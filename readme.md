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
