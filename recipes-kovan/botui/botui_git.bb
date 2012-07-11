inherit qt4x11 cmake

SRC_URI = "git://github.com/kipr/botui.git \
           file://xorg.service \
           file://botui.service \
           file://factory.config"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "r0"

EXTRA_OECMAKE = "-DQT_LIBRARY_DIR=${OE_QMAKE_LIBDIR_QT} \
	-DQT_INCLUDE_DIR=${OE_QMAKE_INCDIR_QT} \
	-DQT_HEADERS_DIR=${OE_QMAKE_INCDIR_QT} \
	-DQT_MOC_EXECUTABLE=${OE_QMAKE_MOC} \
	-DQT_UIC_EXECUTABLE=${OE_QMAKE_UIC} \
	-DQT_UIC3_EXECUTABLE=${OE_QMAKE_UIC3} \
	-DQT_RCC_EXECUTABLE=${OE_QMAKE_RCC} \
	-DQT_QMAKE_EXECUTABLE=${OE_QMAKE_QMAKE} \
	-DQT_QTCORE_INCLUDE_DIR=${OE_QMAKE_INCDIR_QT}/QtCore \
	-DQT_DBUSXML2CPP_EXECUTABLE=/usr/bin/qdbusxml2cpp \
	-DQT_DBUSCPP2XML_EXECUTABLE=/usr/bin/qdbuscpp2xml \
	-DQT_MKSPECS_DIR=${QMAKESPEC}/../ \
	"
OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/xorg.service ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/botui.service ${D}/lib/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../botui.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	install -d ${D}/var/lib/connman
	install -m 0755 ${WORKDIR}/factory.config ${D}/var/lib/connman/

	install -d ${D}/usr/sbin
	install -m 0755 ${S}/botui ${D}/usr/sbin/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/var/lib/connman"