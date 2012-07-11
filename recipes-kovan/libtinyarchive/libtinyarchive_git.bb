inherit cmake

SRC_URI = "git://github.com/kipr/libtinyarchive.git"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4a65f553d5972a638e2f76b78ebbb5da"
PR = "r1"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/libtinyarchive.a ${D}/usr/lib
	
	install -d ${D}/${includedir}
	install -m 0755 ${S}/include/ ${D}/${includedir}
}

FILES_${PN} += "${base_libdir}/libtinyarchive.a"
FILES_${PN} += "${includedir}"