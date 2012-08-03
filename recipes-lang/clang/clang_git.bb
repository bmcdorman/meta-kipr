inherit cmake

SRC_URI = "git://github.com/llvm-mirror/clang.git"
DEPENDS = "llvm2.9"
S = "${WORKDIR}/git"
SRCREV = "5b978519d2c5f5b4541768a827b675e997d4cd34"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE.TXT;md5=dfabea443c6c16b6321441a8c8c19705"
PR = "5b978519d2c5f5b4541768a827b675e997d4cd34"
EXTRA_OECMAKE = "--no-warn-unused-cli"
OECMAKE_SOURCEPATH = "${S}"

FILES_${PN} = "${bindir} ${libdir}"
