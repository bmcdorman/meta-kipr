inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/libkar.git"

DEPENDS = ""

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "r9"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/libkar.so ${D}/usr/lib
	
	install -d ${D}/${includedir}
	install -m 0755 ${S}/kar.hpp ${D}/${includedir}
}

FILES_${PN} += "${libdir}/libkar.so"
FILES_${PN} += "${includedir}/kar.hpp"
