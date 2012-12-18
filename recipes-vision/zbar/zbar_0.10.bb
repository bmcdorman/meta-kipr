DESCRIPTION = "ZBar bar code reader library"
HOMEPAGE = "http://zbar.sourceforge.net/"
MAINTAINER = "Braden McDorman <bmcdorman@kipr.org>"

SRC_URI = "http://downloads.sourceforge.net/project/zbar/zbar/0.10/zbar-0.10.tar.bz2"

inherit autotools

PV = "0.10"
PR = "r1"

S = "${WORKDIR}"

LICENSE = "LGPL 2.1"

EXTRA_OECONF = "--disable-video --without-imagemagick --without-gtk --without-python --without-qt --without-x --without-xshm --without-xv --without-jpeg"