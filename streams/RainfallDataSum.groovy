/*
 *    Copyright 2016 Duncan Dickinson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * Reads weather data from a CSV, filters only readings for
 * February 2008, then calculates the total rainfall for
 * the period.
 */

//Source file: RainfallDataSum.groovy
import static java.nio.file.Paths.get as getFile
@Grab('org.apache.commons:commons-csv:1.2')
import static org.apache.commons.csv.CSVFormat.*

def weatherData = RFC4180.withHeader()
    .parse(getFile('../data/weather_data.csv').newReader())
    .getRecords()

println weatherData.stream()
    .filter { it.Year == '2008' }
    .filter { it.Month == '02' }
    .mapToDouble { record ->
        record.'Rainfall (millimetres)'.toDouble()
    }
    .sum()
