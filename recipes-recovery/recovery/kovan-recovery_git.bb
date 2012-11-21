# The recovery image is rather strange in how it's deployed:
# 1) Build just enough of a kernel to let modules build
# 2) Build recovery console
# 3) Create cpio with output of 2
# 4) Build a kernel with the cpio image attached from 3
# 5) Package the kerne

SRC_URI = "git://github.com/kipr/linux.git;branch=kovan;name=kernel \
           file://defconfig \
"

S = "${WORKDIR}"
SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/kovan-recovery/LICENSE;md5=d32239bcb673463ab874e80d47fae504"
PACKAGE_ARCH = "${MACHINE}"
RECOVERY_IMAGE_ROOTFS = "${WORKDIR}/recovery"
RECOVERY_IMAGE_FILE   = "${WORKDIR}/recovery.cpio"
PR = "r29"
RREPLACES_${PN} = "kovan-recovery-blob"

COMPATIBLE_MACHINE = "kovan"
ONLINE_PACKAGE_MANAGEMENT = "none"
MACHINE_POSTPROCESS_COMMAND = ""

DEPENDS = ""
DEPENDS_append_virtclass_native = "makedevs-native fakeroot-native"

do_fetch() {
	cd ${WORKDIR}
	git clone git://github.com/kipr/kovan-recovery.git
}

do_compile() {
	true
}

do_compile_kernel_pass1() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	export CROSS_COMPILE="${TARGET_PREFIX}"
	cp defconfig git/.config
	cd git; oe_runmake ARCH=arm prepare scripts; cd ..
}

do_compile_recovery() {
	cd kovan-recovery
	LDFLAGS="${LDFLAGS}"
	oe_runmake MY_LIBS="-lm"
	${STRIP} kovan-recovery
}

fakeroot do_populate_kovan_recovery() {
	install -d ${RECOVERY_IMAGE_ROOTFS}
	install -d ${RECOVERY_IMAGE_ROOTFS}/modules
	install -d ${RECOVERY_IMAGE_ROOTFS}/firmware
	install -d ${RECOVERY_IMAGE_ROOTFS}/dev
	install -d ${RECOVERY_IMAGE_ROOTFS}/dev/input

	install -m 0755 ${WORKDIR}/kovan-recovery/kovan-recovery ${RECOVERY_IMAGE_ROOTFS}/init
	# install -m 0755 ${WORKDIR}/AMD.ttf ${RECOVERY_IMAGE_ROOTFS}

	# Extract the C libraries
	cd ${RECOVERY_IMAGE_ROOTFS}
	for i in 'armv5te/libc6_*'
	do
		ar p ${DEPLOY_DIR_IPK}/$i data.tar.gz | tar xz
	done
	
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/mem          c 1 1
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/kmem         c 1 2
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/null         c 1 3
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/zero         c 1 5
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/random       c 1 8
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/urandom      c 1 9
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/kmsg         c 1 11

	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/tty0         c 4 0
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/ttyS0        c 4 64

	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/tty          c 5 0
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/console      c 5 1

	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/input/event0 c 13 64
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/input/event1 c 13 65

	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/fb0          c 29 0

	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/mmcblk0      b 179 0
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/mmcblk0p1    b 179 1
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/mmcblk0p2    b 179 2
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/mmcblk0p3    b 179 3
	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/mmcblk0p4    b 179 4

	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/ttyGS0       c 253 0

	mknod ${RECOVERY_IMAGE_ROOTFS}/dev/ttyUSB0      c 188 0

	cd ${RECOVERY_IMAGE_ROOTFS} && (find . | cpio -o -H newc >${RECOVERY_IMAGE_FILE})
}

do_compile_kernel_pass2() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	export CROSS_COMPILE="${TARGET_PREFIX}"
	cd git
	sed -i 's|^CONFIG_INITRAMFS_SOURCE=.*$|CONFIG_INITRAMFS_SOURCE="${RECOVERY_IMAGE_FILE}"|g' .config
	oe_runmake ARCH=arm
	cd ..
	cp git/arch/arm/boot/zImage ${DEPLOY_DIR_IMAGE}/recovery-mode
}


do_install() {
	install -d ${D}/boot
	install -m 0755 ${DEPLOY_DIR_IMAGE}/recovery-mode ${D}/boot/recovery-mode
}

pkg_postinst_${PN}() {
    if test "x$D" != "x"; then exit 1; fi  # Don't do postinst on build system
    config_util --cmd=putblock --dev=/dev/mmcblk0p1 --block=krnB < /boot/recovery-mode
}

def remove_tasks(deltasks, d):
    for task in filter(lambda k: d.getVarFlag(k, "task"), d.keys()):
        deps = d.getVarFlag(task, "deps")
        for preptask in deltasks:
            if preptask in deps:
                deps.remove(preptask)
        d.setVarFlag(task, "deps", deps)
python () {
    remove_tasks(["do_populate_sysroot", "do_package_update_index_ipk"], d)
}

addtask do_compile_recovery after do_compile do_unpack do_patch
addtask compile_kernel_pass1 after do_unpack before do_compile_recovery
addtask populate_kovan_recovery after do_compile_recovery before do_compile_kernel_pass2
addtask compile_kernel_pass2 after do_populate_kovan_recovery before do_install

FILES_${PN} += "/boot/recovery-mode"

SRC_URI[md5sum] = "2539d0c7283188dadfa312f42f5a82cb"
SRC_URI[sha256sum] = "d567c118c1600087e374483e8b49f748adce3c5727e21333c5f2e986c70de82a"
