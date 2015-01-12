inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/libkar.git"

DEPENDS = ""

S = "${WORKDIR}/git"

SRCREV = "c37b8e3abe1d85018b6614c6798334a765ffb4be"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "r14"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/libkar.so ${D}/usr/lib
	
	install -d ${D}/${includedir}/kar
	install -m 0755 ${S}/include/kar.hpp ${D}/${includedir}/kar
	install -m 0755 ${S}/include/compat.hpp ${D}/${includedir}/kar

	install -d ${D}/${bindir}
	install -m 0775 ${S}/tools/kissarchive ${D}/${bindir}/
}

FILES_${PN} += "${libdir}/libkar.so"
FILES_${PN} += "${bindir}/kissarchive"
FILES_${PN} += "${includedir}/kar.hpp"
