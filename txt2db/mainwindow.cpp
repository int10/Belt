#include "mainwindow.h"
#include "ui_mainwindow.h"
//#include <QSqlDatabase>
//#include <QSqlQuery>
//#include <QSqlError>
//#include <QSettings>
//#include <QSqlRecord>
#include <QDebug>
#include "sqlite3.h"
#include "Sqlite.h"
#include <QFile>

MainWindow::MainWindow(QWidget *parent) :
	QMainWindow(parent),
	ui(new Ui::MainWindow)
{
	ui->setupUi(this);
//	CreateDb();
	CreateEngineDb();
}

MainWindow::~MainWindow()
{
	delete ui;
}



void MainWindow::CreateDb()
{
//	QFile txtfile("d:/dd.txt");
//	txtfile.open(QIODevice::ReadOnly);
//	QByteArray data = txtfile.readAll();
//	QList<QByteArray> datalist = data.split(0x0a);
//	txtfile.close();

//	QList<sBelt> beltlist;
//	sBelt lastbelt;
//	QString lastserial;
//	for(int i = 0; i < datalist.size(); i++) {
//		QList<QByteArray> rlist = datalist.at(i).split(0x09);
//		sBelt belt;
////		belt.serial = "广州宝龙系列";
//		if(rlist.size() != 6) {
//			qDebug()<<"eeeee";
//		} else {
//			belt.serial = lastserial;
//			if(rlist.at(0).endsWith("系列")) {
//				lastserial = rlist.at(0);
//				continue;
//			}
//			if(rlist.at(0).contains("中文车型名称")) continue;


//			belt.model = rlist.at(0).trimmed();
//			belt.modelcode = rlist.at(1).trimmed();
//			belt.engineercode = rlist.at(2).trimmed();
//			belt.akoknum = rlist.at(3).trimmed();
//			belt.factorynum = rlist.at(4).trimmed();
//			belt.pos = rlist.at(5).trimmed();
//			belt.pos.replace(0x0a, "");

//			if(belt.model != lastbelt.model && belt.model != ""){//新车型
//				lastbelt = belt;
//			}

//			if(belt.model == "") belt.model = lastbelt.model;
//			if(belt.modelcode == "") belt.modelcode = lastbelt.modelcode;
//			if(belt.engineercode == "") belt.engineercode = lastbelt.engineercode;

//			qDebug()<<belt.serial<<belt.model<<"|"<<belt.modelcode<<"|"<<belt.engineercode
//				   <<"|"<<belt.akoknum<<"|"<<belt.factorynum<<"|"<<belt.pos;
//			beltlist.append(belt);
//		}
//	}

	Sqlite sqlsrc;
	sqlsrc.Init("d:/aa.db");
	QList<QMap<QString, QByteArray> > srcdata;
	sqlsrc.GetData("select * from belt", srcdata);
	QList<sBelt> beltlist;
//	foreach (QMap<QString, QByteArray> d, srcdata) {
	for(int i = 0; i < srcdata.size(); i++){
		QMap<QString, QByteArray> d = srcdata.at(i);
		sBelt belt;
		belt.serial = QString::fromUtf8(d.value("serial"));
		belt.model = QString::fromUtf8(d.value("model"));
		belt.modelcode = QString::fromUtf8(d.value("modelcode"));
		belt.engineercode = QString::fromUtf8(d.value("engineercode"));
		belt.akoknum = QString::fromUtf8(d.value("akoknum"));
		belt.factorynum = QString::fromUtf8(d.value("factorynum"));
		belt.pos = QString::fromUtf8(d.value("pos"));
		beltlist.append(belt);
	}



	Sqlite sql;
	sql.Init("d:/SysFolder/Desktop/bb.db");
	sql.Exec("create table belt(_id INTEGER PRIMARY KEY AUTOINCREMENT, serial TEXT, model TEXT, modelcode TEXT, engineercode TEXT, "
			 "akoknum TEXT, factorynum TEXT, pos TEXT)");

	sql.Exec("BEGIN");
	for(int i = 0; i < beltlist.count(); i++)
	{
		sBelt belt = beltlist.at(i);
		QString insert = "insert into belt values(";
		insert += "\'" + QString::number(i) + "\',";
		insert += "\'" + belt.serial + "\',";
		insert += "\'" + belt.model + "\',";
		insert += "\'" + belt.modelcode + "\',";
		insert += "\'" + belt.engineercode + "\',";
		insert += "\'" + belt.akoknum + "\',";
		insert += "\'" + belt.factorynum + "\',";
		insert += "\'" + belt.pos + "\'";

		insert += ")";
		sql.Exec(insert.toUtf8());
	}
	sql.Exec("COMMIT");
	sql.Close();
}


void MainWindow::CreateEngineDb()
{
	QFile txtfile("d:/dd.txt");
	txtfile.open(QIODevice::ReadOnly);
	QByteArray data = txtfile.readAll();
	QList<QByteArray> datalist = data.split(0x0a);
	txtfile.close();

	QList<sEngine> enginelist;

	QString lastserial = "";
	for(int i = 0; i < datalist.size(); i++) {
		QList<QByteArray> rlist = datalist.at(i).split(0x09);
		sEngine engine;


		qDebug()<<rlist.size()<<datalist.at(i);

		if(rlist.size() != 3) {
			qDebug()<<"eeeee";
			lastserial = datalist.at(i);
			lastserial.replace(0x0a, "");
			lastserial.replace(0x0d, "");
		} else {
			engine.serial = lastserial;
			engine.model = rlist.at(2).trimmed();
			engine.engineercode = rlist.at(0).trimmed();
			engine.pos = rlist.at(1).trimmed();
			engine.pos.replace(0x0a, "");
			engine.pos.replace(0x0d, "");

			qDebug()<<engine.serial<<engine.model<<"|"<<engine.engineercode
				   <<"|"<<engine.pos;
			enginelist.append(engine);
		}
	}


	Sqlite sql;
	qDebug()<<sql.Init("d:/SysFolder/Desktop/bb.db");
	sql.Exec("create table engine(_id INTEGER PRIMARY KEY AUTOINCREMENT, serial TEXT, model TEXT, engineercode TEXT, "
			 "pos TEXT)");

	sql.Exec("BEGIN");
	for(int i = 0; i < enginelist.count(); i++)
	{
		sEngine engine = enginelist.at(i);
		QString insert = "insert into engine values(";
		insert += "\'" + QString::number(i) + "\',";
		insert += "\'" + engine.serial + "\',";
		insert += "\'" + engine.model + "\',";
		insert += "\'" + engine.engineercode + "\',";
		insert += "\'" + engine.pos + "\'";
		insert += ")";

		sql.Exec(insert.toUtf8());
	}
	sql.Exec("COMMIT");
	sql.Close();
}
