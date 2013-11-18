#include <iostream>
#include <ctime>
#include <syslog.h>

//from: http://stackoverflow.com/questions/997946/how-to-get-current-time-and-date-in-c
const std::string currentDateTime() {
    time_t     now = time(0);
    struct tm  tstruct;
    char       buf[80];
    tstruct = *localtime(&now);
    // Visit http://en.cppreference.com/w/cpp/chrono/c/strftime
    // for more information about date/time format
    strftime(buf, sizeof(buf), "%Y-%m-%d.%X", &tstruct);

    return buf;
}

int main () {
  //Set our Logging Mask and open the Log
  setlogmask(LOG_UPTO(LOG_NOTICE));
  openlog("nagios_app", LOG_CONS | LOG_NDELAY | LOG_PERROR | LOG_PID, LOG_USER);
  
  int i=fork();
  if (i<0) exit(1); /* fork error */
  if (i>0) exit(0); /* parent exits */
  /* child (daemon) continues */

  clock_t begin = clock();
  
  while (true) {
    double elapsed_secs = double(clock() - begin) / CLOCKS_PER_SEC;

    //do some artificial transaction computations
    for (unsigned i = 0; 
	 i < 100000000;
	 i++ ) {
      double tmp = i * i;
    }

    if (elapsed_secs < 5 * 60 * 60) //5 minutes
      syslog(LOG_INFO, (std::string("Processed transaction ") + currentDateTime()).c_str());
  }

  closelog();

  exit(0);
}
