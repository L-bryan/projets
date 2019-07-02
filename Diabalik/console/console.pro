include(../defaults.pri)

INCLUDEPATH += $$PWD/../controllers

TEMPLATE = app
CONFIG += console

LIBS += -L../lib -llibcore \
        -L../lib -llibctrls

HEADERS += \
    printingobservable.h

SOURCES += \
    main.cpp
