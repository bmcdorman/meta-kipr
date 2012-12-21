DESCRIPTION = "ZBar bar code reader library"
HOMEPAGE = "http://zbar.sourceforge.net/"
MAINTAINER = "Braden McDorman <bmcdorman@kipr.org>"

PV = "0.10"
PR = "r7"

SRC_URI = "http://downloads.sourceforge.net/project/zbar/zbar/0.10/zbar-${PV}.tar.bz2"
SRC_URI[md5sum] = "0fd61eb590ac1bab62a77913c8b086a5"

inherit autotools

S = "${WORKDIR}/zbar-${PV}"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=243b725d71bb5df4a1e5920b344b86ad"

EXTRA_OECONF = "--disable-video --without-imagemagick --without-gtk --without-python --without-qt --without-x --without-xshm --without-xv --without-jpeg"

FILES_${PN}-dev = "/usr/include /usr/lib"
FILES_${PN}-staticdev = "/usr/lib/libzbar.a"
FILES_${PN}-doc = "/usr/share"
