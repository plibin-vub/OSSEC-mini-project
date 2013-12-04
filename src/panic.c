//source code from: http://www.faqs.org/docs/Linux-HOWTO/Linux-Crash-HOWTO.html#AEN94

#define __KERNEL__
# MODULE 

# include init_module(void)

int init_module (void)
{
  panic(" panic has been called");
  return 0;
}
