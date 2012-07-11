inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/kiss-compiler.git"

DEPENDS = "kiss-compiler"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "r1"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/lib
	install -m 0755 ${S}/lib/libeasydevice.a ${D}/lib
}

FILES_${PN} += "${libdir}/libeasydevice.a"

