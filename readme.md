<h1>Json from log</h1>
<h2>Сборка</h2>
<p>mvn package</p>
<h2>Использование</h2>
<p>Usage: java -jar jsonfromlog.jar [options] file</p>
  Options:<br>
    -d, --debug     включить отладочную информацию (default: false)<br>
    -h, --help      справка<br>
    -o, --outpath   путь сохранения результатов<br>
    -t, --template  фильтровать по идентификатору шаблона<br>
    -s, --timestamp поиск по штампу времени (формат как в логе, пример: 
                    2020-01-13 13:20:41)<br>
<h1>Пример использования</h1>
<p>java -jar ./jsonfromlog.jar rendering-sync.log</p>                    
<p>java -jar ./jsonfromlog.jar -s "2020-01-09 17:54:30.123" rendering-sync.log</p>                    
<p>java -jar ./jsonfromlog.jar -t ie-fatca -s "2020-01-09 17:54" -o c:\tmp\json rendering-sync.log</p>                    
