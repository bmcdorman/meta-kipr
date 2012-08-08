inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/qextserialport.git"

S = "${WORKDIR}/git"

SRCREV = "9893a2cbaea2263baf85b12bfd5ffe66072ad7d5"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${SR}/LICENSE;md5=bb73ee7e4617fad5f45dc04174212682"
PR = "9893a2cbaea2263baf85b12bfd5ffe66072ad7d5"

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