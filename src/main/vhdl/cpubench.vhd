library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity cpubench is
end cpubench;

architecture test of cpubench is
  constant num_cycles : integer := 60;

  signal iport: std_logic_vector( 15 downto 0) := "0000000000000000";
  signal oport: std_logic_vector(15 downto 0) := "0000000000000000";

  signal PC: std_logic_vector( 7 downto 0) := "00000000";
  signal IR: std_logic_vector(29 downto 0) := "000000000000000000000000000000";
  signal RA: std_logic_vector(15 downto 0) := "0000000000000000";
  signal RB: std_logic_vector(15 downto 0) := "0000000000000000";
  signal RC: std_logic_vector(15 downto 0) := "0000000000000000";
  signal RD: std_logic_vector(15 downto 0) := "0000000000000000";
  signal RE: std_logic_vector(15 downto 0) := "0000000000000000";
  signal RF: std_logic_vector(15 downto 0) := "0000000000000000";
  signal RG: std_logic_vector(15 downto 0) := "0000000000000000";
  signal OUTTEMP1: std_logic_vector(15 downto 0); -- debug purpose
  signal OUTTEMP2: std_logic_vector(15 downto 0); -- debug purpose
  signal clk: std_logic := '1';
  signal reset: std_logic;
  

  component cpu
    port (
      clock   : in  std_logic;                       -- main clock
      reset : in  std_logic;                       -- reset button

      PCview : out std_logic_vector(7 downto 0);
      IRview : out std_logic_vector(29 downto 0);
      
      RAview : out std_logic_vector(15 downto 0);
      RBview : out std_logic_vector(15 downto 0);
      RCview : out std_logic_vector(15 downto 0);
      RDview : out std_logic_vector(15 downto 0);
      REview : out std_logic_vector(15 downto 0);		
      RFview : out std_logic_vector(15 downto 0); -- special internal register
      RGview : out std_logic_vector(15 downto 0); -- halt register
      temp1View: out std_logic_vector(15 downto 0); -- view value for oprand1
      temp2View: out std_logic_vector(15 downto 0); -- view value for oprand2
		
      iport : in  std_logic_vector(15 downto 0);  -- input port
      oport : out std_logic_vector(15 downto 0)); -- output port
  end component;
  
begin

  -- start off with a short reset
  reset <= '0', '1' after 1 ns;

  -- create a clock
  process
  begin
    for i in 1 to num_cycles loop
      clk <= not clk;
      wait for 1 ns;
      
      clk <= not clk;
      wait for 1 ns;
    end loop;
    wait;
  end process;

  iport <= "0000000000000000";


  cpuinstance: cpu
    port map( clk, reset, PC, IR, RA, RB, RC, RD, RE, RF, RG, OUTTEMP1, OUTTEMP2, iport, oport );

  
end test;
