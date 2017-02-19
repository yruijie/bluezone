require 'win32ole'

time = Time.now
day = time.to_a[6]  # day is 0:Sunday, 1:Monday, ...

exit if (day == 0) or (day == 6)

cp_lib = %w(nil CPART1MO CPART2TU CPART3WE CPART4TH CPART5FR) 
Ec_nu = "16"
d_cmd = 'D TOLEC' + Ec_nu 
# bzhao means BlueZone Host Automation Object
bzhao = WIN32OLE.new('BZWhll.WhllObj')
a

sess_name = bzhao.NewSession(1, "STLVM27.zmd")



# Only need to wait 1 senond for the BlueZone mainframe window shows up
# in VM due to low PC performance
sleep 1

conn_result_code = bzhao.Connect(sess_name)

if (conn_result_code != 0) 
  puts "Error! Cannot connect to the display session!"
  exit
end

 
session_id = bzhao.GetSessionId

bzhao.WriteScreen d_cmd, 31, 17  # Input 'D TOLEC...'
bzhao.SendKey "@E" # Input Enter
#sleep 1
bzhao.WaitReady 5, 1000

bzhao.WaitForText("PRESS ENTER KEY FOR LOGON INFORMATION", 24, 1, 5)
bzhao.SendKey "TSO ADMF001<Enter>" 

bzhao.WaitForText("Enter LOGON parameters below", 4, 1, 5)
bzhao.SendKey "c0deshop<Tab>TPROC02"
bzhao.WriteScreen "S", 21, 44  
bzhao.SendKey "@E" # Input Enter

bzhao.WaitForReady
bzhao.WaitForText("***", 1, 1, 5)
bzhao.SendKey "@E" # Input Enter
bzhao.WaitForReady
#bzhao.Pause 500

bzhao.WaitForText("z/OS Primary Option Menu", 3, 1, 5)

bzhao.SendKey "3.4<Enter>" 
bzhao.WaitForReady
bzhao.WaitForText("Data Set List Utility", 3, 30, 5)
bzhao.SendKey "<Tab>SYSADM.JCL.CNTL<Enter>" 
bzhao.WaitForReady
bzhao.WaitForText("SYSADM.JCL.CNTL", 8, 11, 5)
bzhao.WriteScreen "B", 8, 2
bzhao.SendKey "@E" # Input Enter
bzhao.WaitForReady

bzhao.WaitForText("BROWSE            SYSADM.JCL.CNTL", 3, 2, 5)
bzhao.SendKey "F #{cp_lib[day]}<Enter>" 

bzhao.WaitForText(cp_lib[day], 6, 12, 5)
bzhao.WriteScreen "SUB", 6, 2
bzhao.SendKey "@E"
job_text = ""
loop do
  bzhao.SendKey "@E"
  scr_text = bzhao.PSGetText(2560, 1)
  case scr_text
    when /CPART... ENDED AT STLVM3  MAXCC=000[04]/
      job_text = scr_text
      break
    else
      sleep 6
    end # end case

end # end loop

logfile = File.open('C:\YRJ\log\cp_lib_log.txt', "a")
logfile.puts Time.now
logfile.puts job_text.strip!
logfile.puts "*******"
logfile.close

bzhao.SendKey "@E" # Input Enter
bzhao.WaitForReady
bzhao.SendKey "<PF3>" 
bzhao.WaitForReady
bzhao.SendKey "<PF3>" 
bzhao.WaitForReady
bzhao.SendKey "<PF3>"
bzhao.WaitForReady
bzhao.SendKey "<PF3>" 
bzhao.WaitForReady

bzhao.SendKey "2<Enter>" 
bzhao.WaitForReady

bzhao.SendKey "LOGOFF<Enter>" 
bzhao.WaitForReady

bzhao.CloseSession 0, session_id 


