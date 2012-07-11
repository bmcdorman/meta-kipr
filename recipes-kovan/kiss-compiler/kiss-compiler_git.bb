inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/kiss-compiler.git"

DEPENDS = "libtinyarchive"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "r1"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/lib
	install -m 0755 ${WORKDIR}/lib/libkiss-compiler.a ${base_libdir}
}

FILES_${PN} += "${base_libdir}/libkiss-compiler.a"