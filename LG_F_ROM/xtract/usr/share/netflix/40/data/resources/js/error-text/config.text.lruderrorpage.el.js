(function(){var a={language:{name:"en",englishName:"English",nativeName:"English",isRtl:false,language:"en",numberFormat:{pattern:{"0":"-n",length:1},decimals:2,",":",",".":".",groupSizes:{"0":3,length:1},"+":"+","-":"-",percent:{pattern:{"0":"-n %","1":"n %",length:2},decimals:2,groupSizes:{"0":3,length:1},",":",",".":".",symbol:"%"},currency:{pattern:{"0":"($n)","1":"$n",length:2},decimals:2,groupSizes:{"0":3,length:1},",":",",".":".",symbol:"$"}},calendar:{name:"Gregorian_USEnglish","/":"/","&#58;":":",firstDay:0,days:{names:{"0":"Sunday","1":"Monday","2":"Tuesday","3":"Wednesday","4":"Thursday","5":"Friday","6":"Saturday",length:7},namesAbbr:{"0":"Sun","1":"Mon","2":"Tue","3":"Wed","4":"Thu","5":"Fri","6":"Sat",length:7},namesShort:{"0":"Su","1":"Mo","2":"Tu","3":"We","4":"Th","5":"Fr","6":"Sa",length:7}},months:{names:{"0":"January","1":"February","2":"March","3":"April","4":"May","5":"June","6":"July","7":"August","8":"September","9":"October","10":"November","11":"December","12":"",length:13},namesAbbr:{"0":"Jan","1":"Feb","2":"Mar","3":"Apr","4":"May","5":"Jun","6":"Jul","7":"Aug","8":"Sep","9":"Oct","10":"Nov","11":"Dec","12":"",length:13}},AM:{"0":"AM","1":"am","2":"AM",length:3},PM:{"0":"PM","1":"pm","2":"PM",length:3},eras:{"0":{name:"A.D.",start:"",offset:0},length:1},twoDigitYearMax:2029,patterns:{d:"M/d/yyyy",D:"dddd, MMMM dd, yyyy",t:"h:mm tt",T:"h:mm:ss tt",f:"dddd, MMMM dd, yyyy h:mm tt",F:"dddd, MMMM dd, yyyy h:mm:ss tt",M:"MMMM dd",Y:"yyyy MMMM",S:"yyyy'-'MM'-'dd'T'HH':'mm':'ss"}},fontGroup:"WGL"},strings:{customerService:{deactivationConfirmation:"Θέλετε σίγουρα να αποσυνδεθείτε;",deactivationSuccess:"Αποσυνδεθήκατε με επιτυχία.",legend:{back:"Πίσω"},device:{fields:{esn:"ESN",softwareVersion:"Έκδοση λογισμικού",certVersion:"Έκδοση πιστοποιητικού",netflixVersion:"Έκδοση Netflix",deviceModel:"Μοντέλο συσκευής"},netflixVersionTmpl:"nrdapp ${nrdapp} / nrdlib ${nrdlib} / mdxlib ${mdxlib} / sdk ${sdk}"},member:{fields:{name:"Όνομα",email:"Email"},nameTmpl:"${firstName} ${lastName}"},network:{fields:{dnsServers:"Διακομιστές DNS",wired:"Ενσύρματο",wireless:"Ασύρματο"},"default":"Προεπιλογή: Ναι",ipAddressTmpl:"Διεύθυνση IP: ${address}",connectedWifiTmpl:"Συνδεδεμένο: ${connected} - ${ssid}",connectedTmpl:"Συνδεδεμένο: ${connected}",connected:{"true":"Ναι","false":"Όχι"},name:"Όνομα: ${name}"},diagnostics:{results:{noInternet:"Η συσκευή σας ενδέχεται να μην είναι συνδεδεμένη στο Διαδίκτυο. Βεβαιωθείτε ότι η σύνδεσή σας λειτουργεί.",noNetflix:"Αντιμετωπίζουμε πρόβλημα σύνδεσης με το Netflix. Επισκεφθείτε τον ιστότοπο www.netflix.com/help για περισσότερες πληροφορίες.",noProblem:"Επιτυχής έλεγχος δικτύου."}},menu:{close:"Πίσω",member:"Στοιχεία μέλους",network:"Στοιχεία δικτύου",device:"Στοιχεία συσκευής",diagnose:"Ελέγξτε το δίκτυό σας",deactivate:"Αποσύνδεση",back:"Επιστροφή"}},errorPage:{runningDiagnostics:"Προέκυψε σφάλμα στο Netflix. Απόπειρα προσδιορισμού προβλήματος."},networkDiagnostics:{error:{noNetflix:"Αντιμετωπίζουμε πρόβλημα σύνδεσης με το Netflix. Δοκιμάστε ξανά ή επισκεφθείτε τον ιστότοπο: www.netflix.com/help<br/>Κωδικός: ${code}",noInternet:"Η συσκευή σας ενδέχεται να μην είναι συνδεδεμένη στο Διαδίκτυο. Βεβαιωθείτε ότι η σύνδεσή σας λειτουργεί και δοκιμάστε ξανά.<br/>Κωδικός: ${code}",unknown:"Αντιμετωπίζουμε πρόβλημα εκκίνησης του Netflix. Δοκιμάστε ξανά ή επισκεφθείτε τον ιστότοπο: www.netflix.com/help<br/>Κωδικός: ${code}",diagnosisFailure:"Αδυναμία ελέγχου του δικτύου σας. Δοκιμάστε ξανά ή επισκεφθείτε τον ιστότοπο: www.netflix.com/help"}},checkYourNetwork:{netflixServer:"Διακομιστής Netflix ${number}",internetConnection:"Σύνδεση στο Διαδίκτυο",runningCheck:"Εκτελείται έλεγχος...",checkYourNetwork:"Ελέγξτε το δίκτυό σας",introDescription:"Ελέγξτε τη σύνδεσή σας στο Διαδίκτυο για τυχόν προβλήματα που ενδέχεται να μην επιτρέπουν τη χρήση του Netflix."},signOut:{signOut:"Αποσύνδεση",introDescription:"Αποσυνδεθείτε από το δικό σας λογαριασμό Netflix σε αυτή τη συσκευή."},responses:{confirm:"OK",exit:"Έξοδος",no:"Όχι",reactivate:"Αποσύνδεση",retry:"Δοκιμάστε ξανά",yes:"Ναι",customerService:"Περισσότερες λεπτομέρειες"},reactivate:{getNewCredentialsError:"Φαίνεται ότι το Netflix έχει απενεργοποιηθεί σε αυτή τη συσκευή. Ενδέχεται να υπάρχει κάποιο πρόβλημα με το λογαριασμό σας, ή ίσως η συσκευή σας απενεργοποιήθηκε στον ιστότοπο Netflix.com."}}};util.localization.addCulture(a.language.name,a)}());