inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/libkovan.git"

DEPENDS = "opencv i2c-wrapper"
RDEPENDS_${PN} = "i2c-wrapper"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "42"

EXTRA_OECMAKE = "--no-warn-unused-cli -DKOVAN=1"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/libkovan.so ${D}/usr/lib
	
	install -d ${D}/${includedir}/kovan
	install -m 0755 ${S}/include/kovan/*.h ${D}/${includedir}/kovan
	install -m 0755 ${S}/include/kovan/*.hpp ${D}/${includedir}/kovan
}

FILES_${PN} += "${libdir}/libkovan.so"
FILES_${PN} += "${includedir}/kovan"
