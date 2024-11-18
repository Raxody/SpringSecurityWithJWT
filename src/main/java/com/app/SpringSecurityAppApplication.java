package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;


	 @Bean
	 CommandLineRunner init(){
		return args -> {
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();

			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			UserEntity userRaxody = UserEntity.builder()
					.username("raxody")
					.password("$2a$10$tbBFUnfw1p/WhYBjcr5C1.579BneauzmLgwZMj6esiQ14s2oM8v5C")
					.enabled(Boolean.TRUE)
					.accountNoExpired(Boolean.TRUE)
					.accountNoLocked(Boolean.TRUE)
					.credentialsNoExpired(Boolean.TRUE)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userJuan = UserEntity.builder()
					.username("juan")
					.password("$2a$10$tbBFUnfw1p/WhYBjcr5C1.579BneauzmLgwZMj6esiQ14s2oM8v5C")
					.enabled(Boolean.TRUE)
					.accountNoExpired(Boolean.TRUE)
					.accountNoLocked(Boolean.TRUE)
					.credentialsNoExpired(Boolean.TRUE)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAndres = UserEntity.builder()
					.username("andres")
					.password("$2a$10$tbBFUnfw1p/WhYBjcr5C1.579BneauzmLgwZMj6esiQ14s2oM8v5C")
					.enabled(Boolean.TRUE)
					.accountNoExpired(Boolean.TRUE)
					.accountNoLocked(Boolean.TRUE)
					.credentialsNoExpired(Boolean.TRUE)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userNicolle = UserEntity.builder()
					.username("nicolle")
					.password("$2a$10$tbBFUnfw1p/WhYBjcr5C1.579BneauzmLgwZMj6esiQ14s2oM8v5C")
					.enabled(Boolean.TRUE)
					.accountNoExpired(Boolean.TRUE)
					.accountNoLocked(Boolean.TRUE)
					.credentialsNoExpired(Boolean.TRUE)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userRaxody, userJuan, userAndres, userNicolle));

		};
	 }

}
