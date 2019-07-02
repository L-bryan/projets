#ERREUR DE COMPILATION
lors de la compilation il se peut que erreur se produise du type
```c++
no matching function for call to 'BoardGui::BoardGui(Qwidget*&)'
```
Alors pour résoudre le problème il faut
- Dans le fichier ui_test.h
        modifier la ligne 41 par 
        void setupUi(Game* game,Test* test,QWidget *Test)
- Dans le même fichier
        modifier la ligne 47 par 
        widget = new BoardGui(game,test,Test);