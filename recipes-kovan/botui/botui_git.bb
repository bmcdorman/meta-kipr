inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/botui.git \
           file://xorg.service \
           file://botui.service \
           file://cbcnet.config \
           file://settings"

DEPENDS = "libkovan pcompiler easydevice libkar opencv"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "60"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/xorg.service ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/botui.service ${D}/lib/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../botui.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	install -d ${D}/var/lib/connman
	install -m 0755 ../cbcnet.config ${D}/var/lib/connman/
	install -m 0755 ../settings ${D}/var/lib/connman/

	install -d ${D}/usr/sbin
	install -m 0755 ${S}/deploy/botui ${D}/usr/sbin/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/var/lib/connman"
