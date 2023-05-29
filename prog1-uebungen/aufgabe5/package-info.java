/**

Paket mit der f&uuml;nften &Uuml;bungsaufgabe zu Programmiertechnik 1
f&uuml;r AIN/1.

<p>
Innerhalb der Methode
{@link aufgabe5.Notenspiegel#main aufgabe5.Notenspiegel.main}
werden die Namen von F&auml;chern mit zugeh&ouml;rigen Noten
in eine verkettete Liste einlesen. Noten sind Wertobjekte (value objects)
der Klasse {@link aufgabe5.Note aufgabe5.Note}.
Die verkettete Liste {@link aufgabe5.FachnotenListe aufgabe5.FachnotenListe}
enth&auml;lt Objekte der Klasse {@link aufgabe5.Fachnote aufgabe5.Fachnote}.
Nach Eingabeende wird unter der &Uuml;berschrift NOTENSPIEGEL
ein tabellarischer Notenspiegel ausgeben.
In der ersten Spalte stehen linksb&uuml;ndig untereinander die Fachnamen,
in der zweiten Spalte linksb&uuml;ndig untereinander die Noten und
in der dritten Spalte linksb&uuml;ndig ein Vermerk, ob das jeweilige Fach
bestanden ist und wenn ja, ob mit Bestnote.
</p>
<p>
Beispiel (Benutzereingaben sind fett gedruckt):</p>
<pre>
Fach und Note eingeben (Ende mit Strg-D):
<b>Mathematik1 37</b>
<b>Programmiertechnik1 23</b>
<b>Digitaltechnik 1,0</b>
<b>Systemmodellierung 5,0</b>

NOTENSPIEGEL
Systemmodellierung     5,0    nicht bestanden
Digitaltechnik         1,0    mit Bestnote bestanden
Programmiertechnik1    2,3    bestanden
Mathematik1            3,7    bestanden
</pre>

@see
<a href="http://www-home.htwg-konstanz.de/~drachen/prog1/PROG1-Uebungen.pdf">
Anleitungen zu den &Uuml;bungen</a>

@author  H.Drachenfels
@version 22.12.2021

*/

package aufgabe5;

