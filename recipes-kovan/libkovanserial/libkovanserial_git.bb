inherit cmake

SRC_URI = "git://github.com/kipr/libkovanserial.git"

DEPENDS = ""

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "kovan"
SRCREV = "HEAD"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "48"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/libkovanserial.so ${D}/usr/lib
	
	install -d ${D}/${includedir}/kovanserial
	install -m 0755 ${S}/include/kovanserial/*.h ${D}/${includedir}/kovanserial
	install -m 0755 ${S}/include/kovanserial/*.hpp ${D}/${includedir}/kovanserial
}

FILES_${PN} += "${libdir}/libkovanserial.so"
FILES_${PN}-dev += "${includedir}/kovanserial"
