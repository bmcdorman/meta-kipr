inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/botui.git \
           file://xorg.service \
           file://botui.service \
           file://platform.hints \
           file://target.h"

DEPENDS = "libkovan pcompiler easydevice libkar opencv"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "kovan"
SRCREV = "HEAD"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "173"

EXTRA_OECMAKE = "--no-warn-unused-cli"

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

	# Target fixups
	install -d ${D}/usr/include
	install -m 0755 ${WORKDIR}/target.h ${D}/usr/include

	install -d ${D}/usr/sbin
	install -m 0755 ${S}/deploy/botui ${D}/usr/sbin/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/etc/kovan"
FILES_${PN} += "/usr/include"
