inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/qextserialport.git"

S = "${WORKDIR}/git"

SRCREV = "d3fccb363bc12312112a8f758be369b8730fe498"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${SR}/LICENSE;md5=bb73ee7e4617fad5f45dc04174212682"
PR = "d3fccb363bc12312112a8f758be369b8730fe498"

EXTRA_OECMAKE = "--no-warn-unused-cli"
OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/lib*.so ${D}/usr/lib
	
	install -d ${D}/usr/include
	install -m 0755 ${S}/src/qextserialport.h ${D}/usr/include
	install -m 0755 ${S}/src/qextserialport_global.h ${D}/usr/include
	
}

FILES_${PN} += "${libdir}"
FILES_${PN} += "${includedir}"