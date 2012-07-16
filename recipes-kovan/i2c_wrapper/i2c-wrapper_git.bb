inherit cmake

SRC_URI = "git://github.com/kipr/i2c_wrapper.git"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "0.1"

EXTRA_OECMAKE = "--no-warn-unused-cli"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/libi2c_wrapper.so ${D}/usr/lib
	
	install -d ${D}/${includedir}
	install -m 0755 ${S}/include/i2c_wrapper.h ${D}/${includedir}
}

FILES_${PN} += "${libdir}/libi2c_wrapper.so"
FILES_${PN} += "${includedir}"

