SRC_URI = "git://github.com/kipr/i2c_wrapper.git"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "0.1"


do_compile() {
cd ${S}/src
make
}

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/i2c_wrapper.a ${D}/usr/lib
	
	install -d ${D}/${includedir}
	install -m 0755 ${S}/include/i2c_wrapper.h ${D}/${includedir}
}

FILES_${PN} += "${base_libdir}/i2c_wrapper.a"
FILES_${PN} += "${includedir}"

