inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/botui.git \
           file://xorg.service \
           file://botui.service \
           file://platform.hints \
           file://99-calibration.conf"

DEPENDS = "libkovan pcompiler libkar opencv"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "kovan"
SRCREV = "HEAD"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "293"

EXTRA_OECMAKE = "--no-warn-unused-cli -DCMAKE_BUILD_TYPE=Debug"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	# X11 and botui start scripts
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/xorg.service ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/botui.service ${D}/lib/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../botui.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	# Platform.hints TODO: Maybe move out of botui?
	install -d ${D}/etc/kovan
	install -m 0755 ${WORKDIR}/platform.hints ${D}/etc/kovan

	install -d ${D}/kovan

	install -d ${D}/usr/sbin
	install -m 0755 ${S}/deploy/botui ${D}/usr/sbin/
	
	# Default screen calibration
	install -d ${D}/etc/X11/xorg.conf.d
	install -m 0755 ${WORKDIR}/99-calibration.conf ${D}/etc/X11/xorg.conf.d/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/etc"
FILES_${PN} += "/kovan"
FILES_${PN} += "/usr/include"
