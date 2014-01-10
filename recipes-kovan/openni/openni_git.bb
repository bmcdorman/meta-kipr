inherit cmake

SRC_URI = "https://github.com/OpenNI/OpenNI2.git"

DEPENDS = "libusb-dev libudev"
RDEPENDS = "libusb libudev"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "kovan"
SRCREV = "HEAD"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
PR = "205"

EXTRA_OECMAKE = ""

OECMAKE_SOURCEPATH = "${S}"

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${includedir}"
