inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/kovan-serial.git \
           file://kovan-serial.service"

DEPENDS = "libkovanserial libkovan pcompiler libkar"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "kovan"
SRCREV = "HEAD"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "4"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	# X11 and botui start scripts
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/kovan-serial.service ${D}/lib/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../kovan-serial.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	# User directories
	install -d ${D}/kovan
	install -d ${D}/kovan/archives
	install -d ${D}/kovan/binaries

	# Platform.hints
	install -d ${D}/usr/sbin
	install -m 0755 ${S}/deploy/kovan-serial ${D}/usr/sbin/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/kovan"
