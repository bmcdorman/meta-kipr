inherit qt4x11 qmake

SRC_URI = "git://github.com/kipr/qextserialport.git"

SR = "${WORKDIR}/git"
S = "${WORKDIR}/git/buildlib"

SRCREV = "d3fccb363bc12312112a8f758be369b8730fe498"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${SR}/LICENSE;md5=bb73ee7e4617fad5f45dc04174212682"
PR = "d3fccb363bc12312112a8f758be369b8730fe498"

do_compile() {
	cd ${S}
	qmake
	make
}

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${SR}/lib/lib*.so ${D}/usr/lib
	
	install -d ${D}/usr/include
	install -m 0755 ${SR}/src/qextserialport.h ${D}/usr/include
	install -m 0755 ${SR}/src/qextserialport_global.h ${D}/usr/include
	
}

FILES_${PN} += "${libdir}"
FILES_${PN} += "${includedir}"