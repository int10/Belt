#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QList>
#include <QMap>

namespace Ui {
class MainWindow;
}


typedef struct{
	QString serial;
	QString model;
	QString modelcode;
	QString engineercode;
	QString akoknum;
	QString factorynum;
	QString pos;
}sBelt;

typedef struct{
	QString serial;
	QString model;
	QString engineercode;
	QString pos;
}sEngine;

class MainWindow : public QMainWindow
{
	Q_OBJECT

public:
	explicit MainWindow(QWidget *parent = 0);
	~MainWindow();
	void CreateDb();
	void CreateEngineDb();
private:
	Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
