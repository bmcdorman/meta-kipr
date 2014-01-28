inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/pcompiler.git;protocol=git;branch=use_Qt4"
GIT_BRANCH="use_Qt4"
DEPENDS = ""

S = "${WORKDIR}/git"

SRCREV = "5938c0d4868c012a30ccb9acf3980f115e8e0cc8"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "r13"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/lib
	install -m 0755 ${S}/lib/libpcompiler.so ${D}/usr/lib
	
	install -d ${D}/${includedir}/pcompiler
	install -m 0755 ${S}/include/pcompiler/*.hpp ${D}/${includedir}/pcompiler
}

FILES_${PN} += "${libdir}/libpcompiler.so"
FILES_${PN}-dev += "${includedir}/pcompiler"
