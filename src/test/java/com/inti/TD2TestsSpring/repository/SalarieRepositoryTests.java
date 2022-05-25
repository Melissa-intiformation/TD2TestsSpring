package com.inti.TD2TestsSpring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.inti.TD2TestsSpring.model.Salarie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest // = @SpringBootTest (mais ça récupère tout et nous on a juste besoin des
				// repository pour le moment que sur le JPA)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // ne prend pas en compte la base de
																				// données intégrées car nous on veut la
																				// database de mysql
public class SalarieRepositoryTests
{
	@Autowired
	SalarieRepository salarieRepository;

	Salarie s, s2;

	@BeforeEach
	public void setUp()
	{
		s = new Salarie();
		s2 = new Salarie("Toto", "Titi", "tt@gmail.com");
	}

	@Test
	public void testSaveSalarie()
	{
		// Given
		
		// When
		Salarie savedSalarie = salarieRepository.save(s2);
		
		// Then
		assertThat(savedSalarie).isNotNull();
		assertThat(savedSalarie.getId()).isGreaterThan(1);
	}
	
	@Test
	public void testFindAllSalarie() 
	{
		List<Salarie> listeSalarie = salarieRepository.findAll();
	}

}
