#ERREUR DE COMPILATION
lors de la compilation il se peut que erreur se produise du type
```c++
no matching function for call to 'BoardGui::BoardGui(Qwidget*&)'
```
Alors pour r�soudre le probl�me il faut
- Dans le fichier ui_test.h
        modifier la ligne 41 par 
        void setupUi(Game* game,Test* test,QWidget *Test)
- Dans le m�me fichier
        modifier la ligne 47 par 
        widget = new BoardGui(game,test,Test);