DESCRIPTION = "Kovan Kernel Module for FPGA communication"
SRC_URI = "git://github.com/kipr/kovan-kmod.git \
           file://kovan.service"
SRCREV = "HEAD"
SECTION = "kernel/modules"
PRIORITY = "optional"
RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r5"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"
S = "${WORKDIR}/git"

inherit module

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC LD CPP
	oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/kovan-kmod" ' \
		'KERNEL_SOURCE="${STAGING_KERNEL_DIR}" ' \
		'KDIR="${STAGING_KERNEL_DIR}"' \
		'KERNEL_VERSION="${KERNEL_VERSION}" ' \
		'CC="${KERNEL_CC}" ' \
		'LD="${KERNEL_LD}" '

}

do_install () {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/kovan
	install -m 0755 ${S}/kovan${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/kovan/
	
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/kovan.service ${D}/lib/systemd/system
	
	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../kovan.service ${D}${base_libdir}/systemd/system/basic.target.wants/
}

FILES_${PN} = "${base_libdir}/"
