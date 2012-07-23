inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/kiss-compiler.git"

DEPENDS = "libtinyarchive boost"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "r1"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/lib
	install -m 0755 ${S}/lib/libkiss-compiler.a ${D}/lib
	
	install -d ${D}/${includedir}/kiss-compiler
	install -m 0755 ${S}/include/*.h ${D}/${includedir}/kiss-compiler
	
	install -d ${D}/usr/sbin/compilers/
	install -m 0755 ${S}/lib/*.so ${D}/usr/sbin/compilers/
}

FILES_${PN} += "${libdir}/libkiss-compiler.a"
FILES_${PN} += "${includedir}/kiss-compiler"
FILES_${PN} += "/usr/sbin/compilers"
FILES_${PN}-dbg += "/usr/sbin/compilers/.debug"