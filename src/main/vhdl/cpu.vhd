-- Zilin Chen
-- CPU design for one instruction CPU (subneg)
-- CS 232 Final Project

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity cpu is

	port(
		clock	 : in	std_logic;
		reset	 : in	std_logic;
		
		PCview : out std_logic_vector(7 downto 0);  -- debugging outputs
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
		oport : out std_logic_vector(15 downto 0)  -- output port
	);

end entity;

architecture rtl of cpu is
	
	-- create component for ROM
	component dataROM
	port(
		address		: IN STD_LOGIC_VECTOR (7 DOWNTO 0);
		clock		: IN STD_LOGIC  := '1';
		q		: OUT STD_LOGIC_VECTOR (29 DOWNTO 0)
	);
	end component;
	

	-- create component for RAM
	component dataRAM
	port (
		address		: IN STD_LOGIC_VECTOR (7 DOWNTO 0);
		clock		: IN STD_LOGIC  := '1';
		data		: IN STD_LOGIC_VECTOR (15 DOWNTO 0);
		wren		: IN STD_LOGIC ;
		q		: OUT STD_LOGIC_VECTOR (15 DOWNTO 0)
	);
	end component;
	
	

	
	-- Build internal signals
	type state_type is (Start, Fetch, ExecuteA, Execute_Memory_Wait1, Execute_Memory_Wait2, Execute_Memory_Wait3, Execute_Memory_Wait4, Execute_Memory_Wait5, Execute_Memory_Wait6, ExecuteB, Execute_Sub, Execute_Branch, Execute_write, halt );
	
	signal state   : state_type;
	signal internal_counter: unsigned (2 downto 0); -- internal clock
	
	signal RA: unsigned (15 downto 0); -- registers for users
	signal RB: unsigned (15 downto 0);
	signal RC: unsigned (15 downto 0);
	signal RD: unsigned (15 downto 0);
	signal RE: unsigned (15 downto 0);
	signal RF: unsigned (15 downto 0);
	signal RG: unsigned (15 downto 0);
	signal temp1: unsigned (15 downto 0); -- hold immediate value for oprand 1
	signal temp2: unsigned (15 downto 0); -- hold immediate value for oprand 2
		
	signal stk_ptr: unsigned (15 downto 0); -- stack pointer
	signal IR: std_logic_vector (29 downto 0); -- IR
	signal PC: unsigned (7 downto 0); -- PC
	signal CR: std_logic_vector (3 downto 0); 
	signal MBR: std_logic_vector(15 downto 0);
	signal MAR: std_logic_vector(7 downto 0);
	signal OUTREG: std_logic_vector(15 downto 0); -- output
	signal OUTTEMP1: std_logic_vector(15 downto 0); -- debug purpose
	signal OUTTEMP2: std_logic_vector(15 downto 0); -- debug purpose
	signal ROM_data: std_logic_vector(29 downto 0);
	signal RAM_data: std_logic_vector(15 downto 0);
	signal RAM_we: std_logic;
	
	
	
begin

	--port map dataROM
	datarom01:dataROM
	port map(address => std_logic_vector(PC), clock => clock, q => ROM_data);
	
	--port map dataRAM
	dataram01:dataRAM
	port map(address => MAR, clock => clock, data => MBR, wren => RAM_we , q => RAM_data);
	
	
	-- Logic to advance to the next state
	process (clock, reset)
	begin
		if reset = '0' then
			state <= Start;
			internal_counter <= "000";
			
			-- initialization
			PC <= "00000000";
			IR <= "000000000000000000000000000000";
			OUTREG <= "0000000000000000";
			OUTTEMP1 <= "0000000000000000";
			OUTTEMP2 <= "0000000000000000";
			MAR <= "00000000";
			MBR <= "0000000000000000";
			RA <= "0000000000000000";
			RB <= "0000000000000000";
			RC <= "0000000000000000";
			RD <= "0000000000000000";
			RE <= "0000000000000000";
			RF <= "0000000000000000";
			RG <= "0000000000000000";
			temp1 <= "0000000000000000";
			temp2 <= "0000000000000000";
			stk_ptr <= "0000000000000000";
			CR <= "0000";
			

		elsif (rising_edge(clock)) then
			case state is
				--start up: wait for 8 clock cycle
				when Start =>
					--internal counter
					if internal_counter = "111" then
						state <= fetch;
					else
						internal_counter <= internal_counter + 1;
					end if;
				
				
				-- fetch:
				when Fetch =>
					-- goto state Execute_mode
					state <= ExecuteA;
					-- grab instruction from ROM
					IR <= ROM_data;
					-- increment PC
					PC <= PC + 1;
						
				
				-- First oprand
				when ExecuteA =>
					-- set up next state
					if IR(29 downto 27)= "010" or IR(29 downto 27)= "011" then
						state <= Execute_Memory_Wait1;
					else
						state <= ExecuteB;-- load value for second oprand
					end if;
						
				case IR(29 downto 27)is
					-- immediate value store to temp1
					when "000" =>
						temp1 <= unsigned( IR(26)&IR(26)&IR(26)&IR(26)&IR(26)&IR(26)&IR(26)&IR(26)&IR(26 downto 19) );
					-- load from register
					when "001" =>
						case IR(22 downto 19) is
							when "0000" =>
								temp1 <= RA;
							when "0001" =>
								temp1 <= RB;
							when "0010" =>
								temp1 <= RC;
							when "0011" =>
								temp1 <= RD;
							when "0100" => 
								temp1 <= RE;
							when "0101" =>
								temp1 <= RF;
							when "0110" =>
								temp1 <= RG;
							when "0111" =>
								temp1 <= stk_ptr;
							when "1000" =>
								temp1 <= "00000000" & PC;
							when "1001" =>
								temp1 <= unsigned(iport);
							when others =>
								null;
						end case;
					-- load from memory
					when "010" =>
						MAR <= IR(26 downto 19);

					-- use register value for memory address
					when "011" =>
						case IR(22 downto 19) is
							when "0000" =>
								MAR <= std_logic_vector(RA(7 downto 0));
							when "0001" =>
								MAR <= std_logic_vector(RB(7 downto 0));
							when "0010" =>
								MAR <= std_logic_vector(RC(7 downto 0));
							when "0011" =>
								MAR <= std_logic_vector(RD(7 downto 0));
							when "0100" => 
								MAR <= std_logic_vector(RE(7 downto 0));
							when "0101" =>
								MAR <= std_logic_vector(RF(7 downto 0));
							when "0110" =>
								MAR <= std_logic_vector(RG(7 downto 0));
							when "0111" =>
								MAR <= std_logic_vector(stk_ptr(7 downto 0));
							when "1000" =>
								MAR <= std_logic_vector(PC);
							when others =>
								null;
						end case;
					
					when others =>
						null;
				end case;
				
				when Execute_Memory_Wait1 =>
					state <= Execute_Memory_Wait2;
				
				-- memory available
				when Execute_Memory_Wait2 =>
					temp1 <= unsigned(RAM_data);
					state <= ExecuteB;
				
				-- second oprand
				when ExecuteB =>
					OUTTEMP1 <= std_logic_vector(temp1);
					-- set up next state
					if IR(18 downto 16) = "010" or IR(18 downto 16) = "011" then
						state <= Execute_Memory_Wait3;
					elsif RG /= "0000000000000000" then
						state <= Halt;
					else
						state <= Execute_Sub;-- perform subtraction
					end if;
					
					case IR(18 downto 16) is
						--immediate value
						when "000" =>
							temp2 <= unsigned( IR(15)&IR(15)&IR(15)&IR(15)&IR(15)&IR(15)&IR(15)&IR(15)&IR(15 downto 8) );
						-- load from register
						when "001" =>
							case IR(11 downto 8) is
								when "0000" =>
									temp2 <= RA;
								when "0001" =>
									temp2 <= RB;
								when "0010" =>
									temp2 <= RC;
								when "0011" =>
									temp2 <= RD;
								when "0100" => 
									temp2 <= RE;
								when "0101" =>
									temp2 <= RF;
								when "0110" =>
									temp2 <= RG;
								when "0111" =>
									temp2 <= stk_ptr;
								when "1000" =>
									temp2 <= "00000000" & PC;
								when "1010" =>
									temp2 <= unsigned(OUTREG);
								when others =>
									null;
							end case;
						-- load from memory
						when "010" =>
							MAR <= IR(15 downto 8);
						-- use register value for memory address
						when "011" =>
							case IR(11 downto 8) is
								when "0000" =>
									MAR <= std_logic_vector(RA(7 downto 0));
								when "0001" =>
									MAR <= std_logic_vector(RB(7 downto 0));
								when "0010" =>
									MAR <= std_logic_vector(RC(7 downto 0));
								when "0011" =>
									MAR <= std_logic_vector(RD(7 downto 0));
								when "0100" => 
									MAR <= std_logic_vector(RE(7 downto 0));
								when "0101" =>
									MAR <= std_logic_vector(RF(7 downto 0));
								when "0110" =>
									MAR <= std_logic_vector(RG(7 downto 0));
								when "0111" =>
									MAR <= std_logic_vector(stk_ptr(7 downto 0));
								when "1000" =>
									MAR <= std_logic_vector(PC);
								when others =>
									null;
							end case;
					
						when others =>
							null;
						
					end case;
				
				
				when Execute_Memory_Wait3 =>
					state <= Execute_Memory_Wait4;
					
				when Execute_Memory_Wait4 =>
					state <= Execute_Sub;
					temp2 <= unsigned(RAM_data);
					
					
				-- do subtraction
				when Execute_Sub =>
					OUTTEMP2 <= std_logic_vector(temp2);
					if RG /= "0000000000000000" then
						state <= Halt;
					else
						state <= Execute_Branch;
						temp2 <= temp1 - temp2;
					end if;
				
				-- check for branch
				when Execute_Branch =>
					state <= Execute_write;
					if temp2(15) = '1' then
						PC <= unsigned(IR(7 downto 0));
					end if;
				
				-- right the second oprand back to its orignal address
				when Execute_write =>
					-- set up next state
					if IR(18 downto 16) = "010" or IR(18 downto 16) = "011" then
						RAM_we <= '1';
						MBR <= std_logic_vector(temp2);
						state <= Execute_Memory_Wait5;
					else
						state <= Fetch;
					end if;
					
					case IR(18 downto 16) is
						when "000" =>
							OUTREG <= std_logic_vector(temp2);
						--load back to register
						when "001" =>
							case IR(11 downto 8) is
								when "0000" =>
									RA <= temp2;
								when "0001" =>
									RB <= temp2;
								when "0010" =>
									RC <= temp2;
								when "0011" =>
									RD <= temp2;
								when "0100" =>
									RE <= temp2;
								when "0101" =>
									RF <= temp2;
								when "0110" =>
									RG <= temp2;
								when "0111" =>
									stk_ptr <= temp2;
								when "1000" =>
									PC <= temp2(7 downto 0);
								when "1010" =>
									OUTREG <= std_logic_vector(temp2);
								when others =>
									null;
							end case;
						--write back to memory
						when "010" =>
							MAR <= IR(15 downto 8);
						--write back register value memory
						when "011" =>
							case IR(11 downto 8) is
								when "0000" =>
									MAR <= std_logic_vector(RA(7 downto 0));
								when "0001" =>
									MAR <= std_logic_vector(RB(7 downto 0));
								when "0010" =>
									MAR <= std_logic_vector(RC(7 downto 0));
								when "0011" =>
									MAR <= std_logic_vector(RD(7 downto 0));
								when "0100" => 
									MAR <= std_logic_vector(RE(7 downto 0));
								when "0101" =>
									MAR <= std_logic_vector(RF(7 downto 0));
								when "0110" =>
									MAR <= std_logic_vector(RG(7 downto 0));
								when "0111" =>
									MAR <= std_logic_vector(stk_ptr(7 downto 0));
								when "1000" =>
									MAR <= std_logic_vector(PC);
								when others =>
									null;
							end case;
							
						when others =>
							null;
					
					end case;
				
				when Execute_Memory_Wait5 =>
					state <= Execute_Memory_Wait6;
					
				when Execute_Memory_Wait6 =>
					RAM_we <= '0';
					state <= Fetch;
				
				when Halt =>
					state <= Fetch;
					
				when others =>
					null;
					

			
			end case;
		end if;
	end process;

	-- Output assignments
	PCview <= std_logic_vector(PC);
	
	IRview <= std_logic_vector(IR);
	RAview <= std_logic_vector(RA);
	RBview <= std_logic_vector(RB);
	RCview <= std_logic_vector(RC);
	RDview <= std_logic_vector(RD);
	REview <= std_logic_vector(RE);
	RFview <= std_logic_vector(RF);
	RGview <= std_logic_vector(RG);
	temp1View <= OUTTEMP1;
	temp2View <= OUTTEMP2;
	oport <= OUTREG; -- internal output register


end rtl;
