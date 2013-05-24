DESCRIPTION = "libx264"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PR = "r1"

inherit autotools gettext

PROVIDES = "libx264"

EXTRA_OECONF = "--enable-shared --disable-asm"

SRC_URI = "git://git.videolan.org/x264.git \
          "
SRCREV = "76a5c3a19f97cd34b65aeff050de4042b054bc65"

S = "${WORKDIR}/git"

FILES_${PN}-dev += "${libdir}/*.so"

