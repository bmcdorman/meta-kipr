DESCRIPTION = "Kovan FPGA"
HOMEPAGE = "http://www.kipr.org/"
LICENSE = "BSD"
PR = "r6"

SRC_URI = "git://github.com/kipr/kovan-fpga.git \
           file://logo.raw565.gz \
           file://kovan-fpga.rules \
           file://kovan-fpga.service \
           file://fpga-config.py"

SRCREV = "HEAD"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

do_compile() {
	cd ${WORKDIR}
	if [ -e logo.raw565 ]
	then
		gzip logo.raw565
	fi
}

do_install() {
	# fpga module devnode udev rules
	install -d ${D}${base_libdir}/udev
	install -d ${D}${base_libdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/kovan-fpga.rules ${D}${base_libdir}/udev/rules.d/45-kovan-fpga.rules
	
	install -d ${D}${base_libdir}/firmware
	install -m 0644 ${S}/kovan.bit ${D}${base_libdir}/firmware/kovan.bit

	# FPGA firmware
	# This is written into MBR and loaded with u-boot
	install -d ${DEPLOY_DIR_IMAGE}
	install -m 0644 ${S}/kovan.bit ${DEPLOY_DIR_IMAGE}/
	install -m 0644 ${WORKDIR}/logo.raw565.gz ${DEPLOY_DIR_IMAGE}/

	# Startup Service
	install -d ${D}${base_libdir}/systemd/system/
	install -m 0755 ${WORKDIR}/kovan-fpga.service ${D}${base_libdir}/systemd/system
	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../kovan-fpga.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	# Utilities
	install -d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/fpga-config.py ${D}${sbindir}/fpga-config.py
}

FILES_${PN} += "${base_sbindir}"
FILES_${PN} += "${sbindir}"
FILES_${PN} += "${base_libdir}/udev/ ${base_libdir}/systemd"
FILES_${PN} += "${base_libdir}/firmware"

pkg_postinst_${PN}_append() {
	config_util --cmd=putblock --dev=/dev/mmcblk0p1 --block=LX9 < ${base_libdir}/firmware/kovan.bit
}
