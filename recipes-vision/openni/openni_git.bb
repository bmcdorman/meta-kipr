inherit base

MAINTAINER = "Braden McDorman <bmcdorman@kipr.org>"

DEPENDS = "udev libusb"

PR = "r10"

SRC_URI = "git://github.com/kipr/OpenNI2"
COMPATIBLE_MACHINE = "kovan"
SRCREV = "7d0f22ebae80c6ee6c32b7f505c73a90c0a51b8f"


S = "${WORKDIR}/git"

do_compile() {
	cd "${S}"
	make
}

do_install() {
	cd "${S}"
	
	install -d ${D}/${includedir}
	install -m 0755 $(find Include -type f -maxdepth 1) ${D}/${includedir}
	install -d ${D}/${includedir}/Linux-Arm
	install -m 0755 $(find Include/Linux-Arm -type f -maxdepth 1) ${D}/${includedir}/Linux-Arm
	install -d ${D}/${includedir}/Linux-x86
	install -m 0755 $(find Include/Linux-x86 -type f -maxdepth 1) ${D}/${includedir}/Linux-x86

	install -d ${D}/${libdir}
	install -d ${D}/${libdir}/OpenNI2
	install -d ${D}/${libdir}/OpenNI2/Drivers
	install -m 0755 Bin/Arm-Release/lib* ${D}/${libdir}
	install -m 0755 $(find Bin/Arm-Release/OpenNI2/Drivers/ -type f -maxdepth 1) ${D}/${libdir}/OpenNI2/Drivers
}

LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

FILES_${PN} = "/usr/include /usr/lib/libOpenNI2.so /usr/lib/OpenNI2/Drivers/lib*.so"
FILES_${PN}-doc = "/usr/share"
