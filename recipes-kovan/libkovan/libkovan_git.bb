inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/libkovan.git"

DEPENDS = "kiss-compiler"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "r1"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/libkovan.so ${D}/usr/lib
	
	install -d ${D}/${includedir}/libkovan
	install -m 0755 ${S}/include/*.h ${D}/${includedir}/kovan
	install -m 0755 ${S}/include/*.hpp ${D}/${includedir}/kovan
}

FILES_${PN} += "${libdir}/libkovan.so"
FILES_${PN} += "${includedir}/easydevice"
