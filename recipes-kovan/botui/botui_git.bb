inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/botui.git \
           file://xorg.service \
           file://botui.service \
           file://cbcnet.config \
           file://platform.hints \
           file://settings"

DEPENDS = "libkovan pcompiler easydevice libkar opencv-2.4.3"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "95"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	# X11 and botui start scripts
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/xorg.service ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/botui.service ${D}/lib/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../botui.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	# Platform.hints
	install -d ${D}/etc/botui
	install -m 0755 ${WORKDIR}/platform.hints ${D}/etc/botui

	install -d ${D}/usr/sbin
	install -m 0755 ${S}/deploy/botui ${D}/usr/sbin/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/etc/botui"
