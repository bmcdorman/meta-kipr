inherit cmake

SRC_URI = "git://github.com/kipr/libkovan.git"

DEPENDS = "opencv zbar i2c-wrapper libav0.8 openni"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "kovan"
SRCREV = "HEAD"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "288"

EXTRA_OECMAKE = "--no-warn-unused-cli -DKOVAN=1 -DCMAKE_BUILD_TYPE=Debug -DBUILD_DOCUMENTATION=OFF"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/libkovan.so ${D}/usr/lib
	
	install -d ${D}/${includedir}/kovan
	install -m 0755 ${S}/include/kovan/*.h ${D}/${includedir}/kovan
	install -m 0755 ${S}/include/kovan/*.hpp ${D}/${includedir}/kovan
}

FILES_${PN} += "${libdir}/libkovan.so"
FILES_${PN}-dev += "${includedir}/kovan"
