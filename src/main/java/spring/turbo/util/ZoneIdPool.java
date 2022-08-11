/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.time.DateTimeException;
import java.time.ZoneId;

/**
 * @author 应卓
 * @see ZoneId#getAvailableZoneIds()
 * @since 1.1.3
 */
public final class ZoneIdPool {

    /*
        Africa/Abidjan: 格林威治时间
        Africa/Accra: 加纳时间
        Africa/Addis_Ababa: 东非时间
        Africa/Algiers: 中欧时间
        Africa/Asmara: 东非时间
        Africa/Asmera: 东非时间
        Africa/Bamako: 格林威治时间
        Africa/Bangui: 西非时间
        Africa/Banjul: 格林威治时间
        Africa/Bissau: 格林威治时间
        Africa/Blantyre: 中非时间
        Africa/Brazzaville: 西非时间
        Africa/Bujumbura: 中非时间
        Africa/Cairo: 东欧时间
        Africa/Casablanca: 西欧时间
        Africa/Ceuta: 中欧时间
        Africa/Conakry: 格林威治时间
        Africa/Dakar: 格林威治时间
        Africa/Dar_es_Salaam: 东非时间
        Africa/Djibouti: 东非时间
        Africa/Douala: 西非时间
        Africa/El_Aaiun: 西欧时间
        Africa/Freetown: 格林威治时间
        Africa/Gaborone: 中非时间
        Africa/Harare: 中非时间
        Africa/Johannesburg: 南非时间
        Africa/Juba: 中非时间
        Africa/Kampala: 东非时间
        Africa/Khartoum: 中非时间
        Africa/Kigali: 中非时间
        Africa/Kinshasa: 西非时间
        Africa/Lagos: 西非时间
        Africa/Libreville: 西非时间
        Africa/Lome: 格林威治时间
        Africa/Luanda: 西非时间
        Africa/Lubumbashi: 中非时间
        Africa/Lusaka: 中非时间
        Africa/Malabo: 西非时间
        Africa/Maputo: 中非时间
        Africa/Maseru: 南非时间
        Africa/Mbabane: 南非时间
        Africa/Mogadishu: 东非时间
        Africa/Monrovia: 格林威治时间
        Africa/Nairobi: 东非时间
        Africa/Ndjamena: 西非时间
        Africa/Niamey: 西非时间
        Africa/Nouakchott: 格林威治时间
        Africa/Ouagadougou: 格林威治时间
        Africa/Porto-Novo: 西非时间
        Africa/Sao_Tome: 格林威治时间
        Africa/Timbuktu: 格林威治时间
        Africa/Tripoli: 东欧时间
        Africa/Tunis: 中欧时间
        Africa/Windhoek: Central African Time
        America/Adak: 夏威夷时间
        America/Anchorage: 阿拉斯加时间
        America/Anguilla: 大西洋时间
        America/Antigua: 大西洋时间
        America/Araguaina: 巴西利亚时间
        America/Argentina/Buenos_Aires: 阿根廷时间
        America/Argentina/Catamarca: 阿根廷时间
        America/Argentina/ComodRivadavia: 阿根廷时间
        America/Argentina/Cordoba: 阿根廷时间
        America/Argentina/Jujuy: 阿根廷时间
        America/Argentina/La_Rioja: 阿根廷时间
        America/Argentina/Mendoza: 阿根廷时间
        America/Argentina/Rio_Gallegos: 阿根廷时间
        America/Argentina/Salta: 阿根廷时间
        America/Argentina/San_Juan: 阿根廷时间
        America/Argentina/San_Luis: 阿根廷时间
        America/Argentina/Tucuman: 阿根廷时间
        America/Argentina/Ushuaia: 阿根廷时间
        America/Aruba: 大西洋时间
        America/Asuncion: 巴拉圭时间
        America/Atikokan: 东部时间
        America/Atka: 夏威夷时间
        America/Bahia: 巴西利亚时间
        America/Bahia_Banderas: 中部时间
        America/Barbados: 大西洋时间
        America/Belem: 巴西利亚时间
        America/Belize: 中部时间
        America/Blanc-Sablon: 大西洋时间
        America/Boa_Vista: 亚马逊时间
        America/Bogota: 哥伦比亚时间
        America/Boise: 山地时间
        America/Buenos_Aires: 阿根廷时间
        America/Cambridge_Bay: 山地时间
        America/Campo_Grande: 亚马逊时间
        America/Cancun: 东部时间
        America/Caracas: 委内瑞拉时间
        America/Catamarca: 阿根廷时间
        America/Cayenne: 法属圭亚那时间
        America/Cayman: 东部时间
        America/Chicago: 中部时间
        America/Chihuahua: 山地时间
        America/Coral_Harbour: 东部时间
        America/Cordoba: 阿根廷时间
        America/Costa_Rica: 中部时间
        America/Creston: 山地时间
        America/Cuiaba: 亚马逊时间
        America/Curacao: 大西洋时间
        America/Danmarkshavn: 格林威治时间
        America/Dawson: 山地时间
        America/Dawson_Creek: 山地时间
        America/Denver: 山地时间
        America/Detroit: 东部时间
        America/Dominica: 大西洋时间
        America/Edmonton: 山地时间
        America/Eirunepe: Acre 时间
        America/El_Salvador: 中部时间
        America/Ensenada: 太平洋时间
        America/Fort_Nelson: 山地时间
        America/Fort_Wayne: 东部时间
        America/Fortaleza: 巴西利亚时间
        America/Glace_Bay: 大西洋时间
        America/Godthab: 西格林兰岛时间
        America/Goose_Bay: 大西洋时间
        America/Grand_Turk: 东部时间
        America/Grenada: 大西洋时间
        America/Guadeloupe: 大西洋时间
        America/Guatemala: 中部时间
        America/Guayaquil: 厄瓜多尔时间
        America/Guyana: 圭亚那时间
        America/Halifax: 大西洋时间
        America/Havana: 古巴时间
        America/Hermosillo: 山地时间
        America/Indiana/Indianapolis: 东部时间
        America/Indiana/Knox: 中部时间
        America/Indiana/Marengo: 东部时间
        America/Indiana/Petersburg: 东部时间
        America/Indiana/Tell_City: 中部时间
        America/Indiana/Vevay: 东部时间
        America/Indiana/Vincennes: 东部时间
        America/Indiana/Winamac: 东部时间
        America/Indianapolis: 东部时间
        America/Inuvik: 山地时间
        America/Iqaluit: 东部时间
        America/Jamaica: 东部时间
        America/Jujuy: 阿根廷时间
        America/Juneau: 阿拉斯加时间
        America/Kentucky/Louisville: 东部时间
        America/Kentucky/Monticello: 东部时间
        America/Knox_IN: 中部时间
        America/Kralendijk: 大西洋时间
        America/La_Paz: 玻利维亚时间
        America/Lima: 秘鲁时间
        America/Los_Angeles: 太平洋时间
        America/Louisville: 东部时间
        America/Lower_Princes: 大西洋时间
        America/Maceio: 巴西利亚时间
        America/Managua: 中部时间
        America/Manaus: 亚马逊时间
        America/Marigot: 大西洋时间
        America/Martinique: 大西洋时间
        America/Matamoros: 中部时间
        America/Mazatlan: 山地时间
        America/Mendoza: 阿根廷时间
        America/Menominee: 中部时间
        America/Merida: 中部时间
        America/Metlakatla: 阿拉斯加时间
        America/Mexico_City: 中部时间
        America/Miquelon: 皮埃尔和密克隆岛时间
        America/Moncton: 大西洋时间
        America/Monterrey: 中部时间
        America/Montevideo: 乌拉圭时间
        America/Montreal: 东部时间
        America/Montserrat: 大西洋时间
        America/Nassau: 东部时间
        America/New_York: 东部时间
        America/Nipigon: 东部时间
        America/Nome: 阿拉斯加时间
        America/Noronha: 费尔南多德诺罗尼亚时间
        America/North_Dakota/Beulah: 中部时间
        America/North_Dakota/Center: 中部时间
        America/North_Dakota/New_Salem: 中部时间
        America/Nuuk: 西格林兰岛时间
        America/Ojinaga: 山地时间
        America/Panama: 东部时间
        America/Pangnirtung: 东部时间
        America/Paramaribo: 苏利南时间
        America/Phoenix: 山地时间
        America/Port-au-Prince: 东部时间
        America/Port_of_Spain: 大西洋时间
        America/Porto_Acre: Acre 时间
        America/Porto_Velho: 亚马逊时间
        America/Puerto_Rico: 大西洋时间
        America/Punta_Arenas: America/Punta_Arenas
        America/Rainy_River: 中部时间
        America/Rankin_Inlet: 中部时间
        America/Recife: 巴西利亚时间
        America/Regina: 中部时间
        America/Resolute: 中部时间
        America/Rio_Branco: Acre 时间
        America/Rosario: 阿根廷时间
        America/Santa_Isabel: 太平洋时间
        America/Santarem: 巴西利亚时间
        America/Santiago: 智利时间
        America/Santo_Domingo: 大西洋时间
        America/Sao_Paulo: 巴西利亚时间
        America/Scoresbysund: 东格林岛时间
        America/Shiprock: 山地时间
        America/Sitka: 阿拉斯加时间
        America/St_Barthelemy: 大西洋时间
        America/St_Johns: 纽芬兰时间
        America/St_Kitts: 大西洋时间
        America/St_Lucia: 大西洋时间
        America/St_Thomas: 大西洋时间
        America/St_Vincent: 大西洋时间
        America/Swift_Current: 中部时间
        America/Tegucigalpa: 中部时间
        America/Thule: 大西洋时间
        America/Thunder_Bay: 东部时间
        America/Tijuana: 太平洋时间
        America/Toronto: 东部时间
        America/Tortola: 大西洋时间
        America/Vancouver: 太平洋时间
        America/Virgin: 大西洋时间
        America/Whitehorse: 山地时间
        America/Winnipeg: 中部时间
        America/Yakutat: 阿拉斯加时间
        America/Yellowknife: 山地时间
        Antarctica/Casey: 西部时间 (澳大利亚)
        Antarctica/Davis: 戴维斯时间
        Antarctica/DumontDUrville: Dumont-d'Urville 时间
        Antarctica/Macquarie: Australian Eastern Time (Macquarie)
        Antarctica/Mawson: 莫森时间
        Antarctica/McMurdo: 新西兰时间
        Antarctica/Palmer: 智利时间
        Antarctica/Rothera: 罗瑟拉时间
        Antarctica/South_Pole: 新西兰时间
        Antarctica/Syowa: Syowa 时间
        Antarctica/Troll: Troll Time
        Antarctica/Vostok: 莫斯托克时间
        Arctic/Longyearbyen: 中欧时间
        Asia/Aden: 阿拉伯半岛时间
        Asia/Almaty: Alma-Ata 时间
        Asia/Amman: 东欧时间
        Asia/Anadyr: 阿那底河时间
        Asia/Aqtau: Aqtau 时间
        Asia/Aqtobe: Aqtobe 时间
        Asia/Ashgabat: 土库曼时间
        Asia/Ashkhabad: 土库曼时间
        Asia/Atyrau: Asia/Atyrau
        Asia/Baghdad: 阿拉伯半岛时间
        Asia/Bahrain: 阿拉伯半岛时间
        Asia/Baku: 亚塞拜然时间
        Asia/Bangkok: 印度支那时间
        Asia/Barnaul: Asia/Barnaul
        Asia/Beirut: 东欧时间
        Asia/Bishkek: 吉尔吉斯斯坦时间
        Asia/Brunei: 文莱时间
        Asia/Calcutta: 印度时间
        Asia/Chita: 亚库次克时间
        Asia/Choibalsan: Choibalsan 时间
        Asia/Chongqing: 中国时间
        Asia/Chungking: 中国时间
        Asia/Colombo: 印度时间
        Asia/Dacca: 孟加拉时间
        Asia/Damascus: 东欧时间
        Asia/Dhaka: 孟加拉时间
        Asia/Dili: 东帝汶时间
        Asia/Dubai: 海湾时间
        Asia/Dushanbe: 塔吉克斯坦时间
        Asia/Famagusta: Asia/Famagusta
        Asia/Gaza: 东欧时间
        Asia/Harbin: 中国时间
        Asia/Hebron: 东欧时间
        Asia/Ho_Chi_Minh: 印度支那时间
        Asia/Hong_Kong: 香港时间
        Asia/Hovd: 科布多时间
        Asia/Irkutsk: 伊尔库次克时间
        Asia/Istanbul: 东欧时间
        Asia/Jakarta: 西印度尼西亚时间
        Asia/Jayapura: 东印度尼西亚时间
        Asia/Jerusalem: 以色列时间
        Asia/Kabul: 阿富汗时间
        Asia/Kamchatka: 彼得罗巴甫洛夫斯克时间
        Asia/Karachi: 巴基斯坦时间
        Asia/Kashgar: 中国时间
        Asia/Kathmandu: 尼泊尔时间
        Asia/Katmandu: 尼泊尔时间
        Asia/Khandyga: 亚库次克时间
        Asia/Kolkata: 印度时间
        Asia/Krasnoyarsk: 克拉斯诺亚尔斯克时间
        Asia/Kuala_Lumpur: 马来西亚时间
        Asia/Kuching: 马来西亚时间
        Asia/Kuwait: 阿拉伯半岛时间
        Asia/Macao: 中国时间
        Asia/Macau: 中国时间
        Asia/Magadan: Magadan 时间
        Asia/Makassar: 中部印度尼西亚时间
        Asia/Manila: Philippines Time
        Asia/Muscat: 海湾时间
        Asia/Nicosia: 东欧时间
        Asia/Novokuznetsk: 克拉斯诺亚尔斯克时间
        Asia/Novosibirsk: Novosibirsk 时间
        Asia/Omsk: 鄂木斯克时间
        Asia/Oral: Oral 时间
        Asia/Phnom_Penh: 印度支那时间
        Asia/Pontianak: 西印度尼西亚时间
        Asia/Pyongyang: 韩国时间
        Asia/Qatar: 阿拉伯半岛时间
        Asia/Qostanay: Asia/Qostanay
        Asia/Qyzylorda: Qyzylorda 时间
        Asia/Rangoon: 缅甸时间
        Asia/Riyadh: 阿拉伯半岛时间
        Asia/Saigon: 印度支那时间
        Asia/Sakhalin: 库页岛时间
        Asia/Samarkand: 乌兹别克斯坦时间
        Asia/Seoul: 韩国时间
        Asia/Shanghai: 中国时间
        Asia/Singapore: 新加坡时间
        Asia/Srednekolymsk: Srednekolymsk Time
        Asia/Taipei: 中国时间
        Asia/Tashkent: 乌兹别克斯坦时间
        Asia/Tbilisi: 乔治亚时间
        Asia/Tehran: 伊朗时间
        Asia/Tel_Aviv: 以色列时间
        Asia/Thimbu: 不丹时间
        Asia/Thimphu: 不丹时间
        Asia/Tokyo: 日本时间
        Asia/Tomsk: Asia/Tomsk
        Asia/Ujung_Pandang: 中部印度尼西亚时间
        Asia/Ulaanbaatar: 库伦时间
        Asia/Ulan_Bator: 库伦时间
        Asia/Urumqi: 中国时间
        Asia/Ust-Nera: 乌斯季涅拉时间
        Asia/Vientiane: 印度支那时间
        Asia/Vladivostok: 海参崴时间
        Asia/Yakutsk: 亚库次克时间
        Asia/Yangon: 缅甸时间
        Asia/Yekaterinburg: Yekaterinburg 时间
        Asia/Yerevan: 亚美尼亚时间
        Atlantic/Azores: 亚速尔群岛时间
        Atlantic/Bermuda: 大西洋时间
        Atlantic/Canary: 西欧时间
        Atlantic/Cape_Verde: 佛德角时间
        Atlantic/Faeroe: 西欧时间
        Atlantic/Faroe: 西欧时间
        Atlantic/Jan_Mayen: 中欧时间
        Atlantic/Madeira: 西欧时间
        Atlantic/Reykjavik: 格林威治时间
        Atlantic/South_Georgia: 南乔治亚岛时间
        Atlantic/St_Helena: 格林威治时间
        Atlantic/Stanley: 福克兰群岛时间
        Australia/ACT: 东部时间 (新南威尔斯)
        Australia/Adelaide: 中部时间 (南澳大利亚)
        Australia/Brisbane: 东部时间 (昆士兰)
        Australia/Broken_Hill: 中部时间 (南澳大利亚/新南威尔斯)
        Australia/Canberra: 东部时间 (新南威尔斯)
        Australia/Currie: 东部时间 (新南威尔斯)
        Australia/Darwin: 中部时间 (北部地区)
        Australia/Eucla: 中西部时间 (澳大利亚)
        Australia/Hobart: 东部时间 (塔斯马尼亚)
        Australia/LHI: 豪公时间
        Australia/Lindeman: 东部时间 (昆士兰)
        Australia/Lord_Howe: 豪公时间
        Australia/Melbourne: 东部时间 (维多利亚)
        Australia/NSW: 东部时间 (新南威尔斯)
        Australia/North: 中部时间 (北部地区)
        Australia/Perth: 西部时间 (澳大利亚)
        Australia/Queensland: 东部时间 (昆士兰)
        Australia/South: 中部时间 (南澳大利亚)
        Australia/Sydney: 东部时间 (新南威尔斯)
        Australia/Tasmania: 东部时间 (塔斯马尼亚)
        Australia/Victoria: 东部时间 (维多利亚)
        Australia/West: 西部时间 (澳大利亚)
        Australia/Yancowinna: 中部时间 (南澳大利亚/新南威尔斯)
        Brazil/Acre: Acre 时间
        Brazil/DeNoronha: 费尔南多德诺罗尼亚时间
        Brazil/East: 巴西利亚时间
        Brazil/West: 亚马逊时间
        CET: 中欧时间
        CST6CDT: 中部时间
        Canada/Atlantic: 大西洋时间
        Canada/Central: 中部时间
        Canada/Eastern: 东部时间
        Canada/Mountain: 山地时间
        Canada/Newfoundland: 纽芬兰时间
        Canada/Pacific: 太平洋时间
        Canada/Saskatchewan: 中部时间
        Canada/Yukon: 山地时间
        Chile/Continental: 智利时间
        Chile/EasterIsland: 复活岛时间
        Cuba: 古巴时间
        EET: 东欧时间
        EST5EDT: 东部时间
        Egypt: 东欧时间
        Eire: 爱尔兰时间
        Etc/GMT: 格林威治时间
        Etc/GMT+0: 格林威治时间
        Etc/GMT+1: Etc/GMT+1
        Etc/GMT+10: Etc/GMT+10
        Etc/GMT+11: Etc/GMT+11
        Etc/GMT+12: Etc/GMT+12
        Etc/GMT+2: Etc/GMT+2
        Etc/GMT+3: Etc/GMT+3
        Etc/GMT+4: Etc/GMT+4
        Etc/GMT+5: Etc/GMT+5
        Etc/GMT+6: Etc/GMT+6
        Etc/GMT+7: Etc/GMT+7
        Etc/GMT+8: Etc/GMT+8
        Etc/GMT+9: Etc/GMT+9
        Etc/GMT-0: 格林威治时间
        Etc/GMT-1: Etc/GMT-1
        Etc/GMT-10: Etc/GMT-10
        Etc/GMT-11: Etc/GMT-11
        Etc/GMT-12: Etc/GMT-12
        Etc/GMT-13: Etc/GMT-13
        Etc/GMT-14: Etc/GMT-14
        Etc/GMT-2: Etc/GMT-2
        Etc/GMT-3: Etc/GMT-3
        Etc/GMT-4: Etc/GMT-4
        Etc/GMT-5: Etc/GMT-5
        Etc/GMT-6: Etc/GMT-6
        Etc/GMT-7: Etc/GMT-7
        Etc/GMT-8: Etc/GMT-8
        Etc/GMT-9: Etc/GMT-9
        Etc/GMT0: 格林威治时间
        Etc/Greenwich: 格林威治时间
        Etc/UCT: 协调世界时间
        Etc/UTC: 协调世界时间
        Etc/Universal: 协调世界时间
        Etc/Zulu: 协调世界时间
        Europe/Amsterdam: 中欧时间
        Europe/Andorra: 中欧时间
        Europe/Astrakhan: Europe/Astrakhan
        Europe/Athens: 东欧时间
        Europe/Belfast: 英国时间
        Europe/Belgrade: 中欧时间
        Europe/Berlin: 中欧时间
        Europe/Bratislava: 中欧时间
        Europe/Brussels: 中欧时间
        Europe/Bucharest: 东欧时间
        Europe/Budapest: 中欧时间
        Europe/Busingen: 中欧时间
        Europe/Chisinau: 东欧时间
        Europe/Copenhagen: 中欧时间
        Europe/Dublin: 爱尔兰时间
        Europe/Gibraltar: 中欧时间
        Europe/Guernsey: 英国时间
        Europe/Helsinki: 东欧时间
        Europe/Isle_of_Man: 英国时间
        Europe/Istanbul: 东欧时间
        Europe/Jersey: 英国时间
        Europe/Kaliningrad: 东欧时间
        Europe/Kiev: 东欧时间
        Europe/Kirov: Europe/Kirov
        Europe/Lisbon: 西欧时间
        Europe/Ljubljana: 中欧时间
        Europe/London: 英国时间
        Europe/Luxembourg: 中欧时间
        Europe/Madrid: 中欧时间
        Europe/Malta: 中欧时间
        Europe/Mariehamn: 东欧时间
        Europe/Minsk: 莫斯科时间
        Europe/Monaco: 中欧时间
        Europe/Moscow: 莫斯科时间
        Europe/Nicosia: 东欧时间
        Europe/Oslo: 中欧时间
        Europe/Paris: 中欧时间
        Europe/Podgorica: 中欧时间
        Europe/Prague: 中欧时间
        Europe/Riga: 东欧时间
        Europe/Rome: 中欧时间
        Europe/Samara: 沙马拉时间
        Europe/San_Marino: 中欧时间
        Europe/Sarajevo: 中欧时间
        Europe/Saratov: Europe/Saratov
        Europe/Simferopol: 莫斯科时间
        Europe/Skopje: 中欧时间
        Europe/Sofia: 东欧时间
        Europe/Stockholm: 中欧时间
        Europe/Tallinn: 东欧时间
        Europe/Tirane: 中欧时间
        Europe/Tiraspol: 东欧时间
        Europe/Ulyanovsk: Europe/Ulyanovsk
        Europe/Uzhgorod: 东欧时间
        Europe/Vaduz: 中欧时间
        Europe/Vatican: 中欧时间
        Europe/Vienna: 中欧时间
        Europe/Vilnius: 东欧时间
        Europe/Volgograd: 莫斯科时间
        Europe/Warsaw: 中欧时间
        Europe/Zagreb: 中欧时间
        Europe/Zaporozhye: 东欧时间
        Europe/Zurich: 中欧时间
        GB: 英国时间
        GB-Eire: 英国时间
        GMT: 格林威治时间
        GMT0: 格林威治时间
        Greenwich: 格林威治时间
        Hongkong: 香港时间
        Iceland: 格林威治时间
        Indian/Antananarivo: 东非时间
        Indian/Chagos: 印度洋地带时间
        Indian/Christmas: 圣诞岛时间
        Indian/Cocos: 可可斯群岛时间
        Indian/Comoro: 东非时间
        Indian/Kerguelen: 法属南极时间
        Indian/Mahe: 塞席尔群岛时间
        Indian/Maldives: 马尔代夫时间
        Indian/Mauritius: 摩里西斯时间
        Indian/Mayotte: 东非时间
        Indian/Reunion: 留尼旺岛时间
        Iran: 伊朗时间
        Israel: 以色列时间
        Jamaica: 东部时间
        Japan: 日本时间
        Kwajalein: 马绍尔群岛时间
        Libya: 东欧时间
        MET: MET
        MST7MDT: 山地时间
        Mexico/BajaNorte: 太平洋时间
        Mexico/BajaSur: 山地时间
        Mexico/General: 中部时间
        NZ: 新西兰时间
        NZ-CHAT: 查塔姆时间
        Navajo: 山地时间
        PRC: 中国时间
        PST8PDT: 太平洋时间
        Pacific/Apia: 西萨摩亚时间
        Pacific/Auckland: 新西兰时间
        Pacific/Bougainville: Bougainville Time
        Pacific/Chatham: 查塔姆时间
        Pacific/Chuuk: 丘克时间
        Pacific/Easter: 复活岛时间
        Pacific/Efate: 瓦奴阿图时间
        Pacific/Enderbury: 菲尼克斯群岛时间
        Pacific/Fakaofo: 托克劳群岛时间
        Pacific/Fiji: 斐济时间
        Pacific/Funafuti: 吐鲁瓦时间
        Pacific/Galapagos: 加拉巴哥时间
        Pacific/Gambier: 冈比亚时间
        Pacific/Guadalcanal: 所罗门群岛时间
        Pacific/Guam: 查摩洛时间
        Pacific/Honolulu: 夏威夷时间
        Pacific/Johnston: 夏威夷时间
        Pacific/Kanton: 菲尼克斯群岛时间
        Pacific/Kiritimati: Line 岛时间
        Pacific/Kosrae: Kosrae 时间
        Pacific/Kwajalein: 马绍尔群岛时间
        Pacific/Majuro: 马绍尔群岛时间
        Pacific/Marquesas: 马克萨斯时间
        Pacific/Midway: 萨摩亚时间
        Pacific/Nauru: 诺鲁时间
        Pacific/Niue: 纽威岛时间
        Pacific/Norfolk: 诺福克时间
        Pacific/Noumea: 新加勒多尼亚时间
        Pacific/Pago_Pago: 萨摩亚时间
        Pacific/Palau: 帛琉时间
        Pacific/Pitcairn: 皮特凯恩时间
        Pacific/Pohnpei: 波纳佩时间
        Pacific/Ponape: 波纳佩时间
        Pacific/Port_Moresby: 巴布亚新几内亚时间
        Pacific/Rarotonga: 库克群岛时间
        Pacific/Saipan: 查摩洛时间
        Pacific/Samoa: 萨摩亚时间
        Pacific/Tahiti: 大溪地岛时间
        Pacific/Tarawa: 吉伯特群岛时间
        Pacific/Tongatapu: 东加时间
        Pacific/Truk: 丘克时间
        Pacific/Wake: 威克时间
        Pacific/Wallis: 瓦利斯及福杜纳群岛时间
        Pacific/Yap: 丘克时间
        Poland: 中欧时间
        Portugal: 西欧时间
        ROK: 韩国时间
        Singapore: 新加坡时间
        SystemV/AST4: 大西洋时间
        SystemV/AST4ADT: 大西洋时间
        SystemV/CST6: 中部时间
        SystemV/CST6CDT: 中部时间
        SystemV/EST5: 东部时间
        SystemV/EST5EDT: 东部时间
        SystemV/HST10: 夏威夷时间
        SystemV/MST7: 山地时间
        SystemV/MST7MDT: 山地时间
        SystemV/PST8: 太平洋时间
        SystemV/PST8PDT: 太平洋时间
        SystemV/YST9: 阿拉斯加时间
        SystemV/YST9YDT: 阿拉斯加时间
        Turkey: 东欧时间
        UCT: 协调世界时间
        US/Alaska: 阿拉斯加时间
        US/Aleutian: 夏威夷时间
        US/Arizona: 山地时间
        US/Central: 中部时间
        US/East-Indiana: 东部时间
        US/Eastern: 东部时间
        US/Hawaii: 夏威夷时间
        US/Indiana-Starke: 中部时间
        US/Michigan: 东部时间
        US/Mountain: 山地时间
        US/Pacific: 太平洋时间
        US/Samoa: 萨摩亚时间
        UTC: 协调世界时间
        Universal: 协调世界时间
        W-SU: 莫斯科时间
        WET: 西欧时间
        Zulu: 协调世界时间
    */

    public static ZoneId toZoneIdOrDefault(@Nullable String name) {
        return toZoneIdOrDefault(name, SYSTEM_DEFAULT);
    }

    public static ZoneId toZoneIdOrDefault(@Nullable String name, ZoneId defaultIfNullOrError) {
        Asserts.notNull(defaultIfNullOrError);

        if (name != null) {
            try {
                return ZoneId.of(name);
            } catch (DateTimeException e) {
                throw new RuntimeException(e);
            }
        } else {
            return defaultIfNullOrError;
        }
    }

    // SYSTEM
    public static final ZoneId SYSTEM_DEFAULT = ZoneId.systemDefault();

    // UTC
    public static final String EUROPE_LONDON_VALUE = "Europe/London";
    public static final ZoneId EUROPE_LONDON = ZoneId.of(EUROPE_LONDON_VALUE);

    // UTC+8
    public static final String ASIA_SHANGHAI_VALUE = "Asia/Shanghai";
    public static final ZoneId ASIA_SHANGHAI = ZoneId.of(ASIA_SHANGHAI_VALUE);

    // UTC+9
    public static final String ASIA_TOKYO_VALUE = "Asia/Tokyo";
    public static final ZoneId ASIA_TOKYO = ZoneId.of(ASIA_TOKYO_VALUE);

    /**
     * 私有构造方法
     */
    private ZoneIdPool() {
        super();
    }

}
