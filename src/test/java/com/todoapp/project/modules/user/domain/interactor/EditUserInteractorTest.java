package com.todoapp.project.modules.user.domain.interactor;


/*
@ExtendWith(MockitoExtension.class)
class EditUserInteractorTest {

    @InjectMocks
    private EditUserInteractor editUserInteractor;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEditMapper userEditMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordValidator passwordValidator;

    private UUID executerId;
    private UUID userId;
    private UserEditRequest editRequest;
    private UserEntity executerUser;
    private UserEntity userToEdit;
    private UserEditResponse editResponse;

    @BeforeEach
    void setUp() {
        executerId = UUID.randomUUID();
        userId = UUID.randomUUID();

        editRequest = new UserEditRequest("New Name", "new.email@example.com");

        executerUser = spy(new UserEntity(
                executerId,
                new Name("Admin Executer"),
                new Email("admin@example.com"),
                Password.fromPlain("password", passwordEncoder, passwordValidator),
                TypeUser.ADMIN,
                null, null, 0
        ));

        userToEdit = spy(new UserEntity(
                userId,
                new Name("Old Name"),
                new Email("old.email@example.com"),
                Password.fromPlain("password", passwordEncoder, passwordValidator),
                TypeUser.NORMAL,
                null, null, 0
        ));

        editResponse = new UserEditResponse(userId, "New Name", "new.email@example.com");
    }

    @Test
    @DisplayName("Should edit user successfully when executer is an ADMIN")
    void execute_shouldEditSuccessfully_whenExecuterIsAdmin() {
        when(userRepository.findById(executerId)).thenReturn(Optional.of(executerUser));
        when(userRepository.findById(userId)).thenReturn(Optional.of(userToEdit));
        when(userEditMapper.toResponse(any(UserEntity.class))).thenReturn(editResponse);

        UserEditResponse result = editUserInteractor.execute(editRequest, executerId, userId);

        verify(userRepository, times(2)).findById(any(UUID.class));
        verify(userRepository, times(1)).save(userToEdit);
        verify(userEditMapper, times(1)).toEntity(editRequest, userToEdit);
        verify(userEditMapper, times(1)).toResponse(userToEdit);
        assertEquals(editResponse, result);
    }

    @Test
    @DisplayName("Should edit user successfully when executer is editing self")
    void execute_shouldEditSuccessfully_whenEditingSelf() {
        when(userRepository.findById(executerId)).thenReturn(Optional.of(executerUser));
        when(userEditMapper.toResponse(any(UserEntity.class))).thenReturn(editResponse);

        // Simula a edição de si mesmo
        UserEditResponse result = editUserInteractor.execute(editRequest, executerId, executerId);

        verify(userRepository, times(2)).findById(any(UUID.class));
        verify(userRepository, times(1)).save(executerUser);
        verify(userEditMapper, times(1)).toEntity(editRequest, executerUser);
        verify(userEditMapper, times(1)).toResponse(executerUser);
        assertEquals(editResponse, result);
    }

    @Test
    @DisplayName("Should throw exception when executer user is not found")
    void execute_shouldThrowException_whenExecuterNotFound() {
        when(userRepository.findById(executerId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundByIdException.class, () -> {
            editUserInteractor.execute(editRequest, executerId, userId);
        });

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should throw exception when user to be edited is not found")
    void execute_shouldThrowException_whenUserToEditNotFound() {
        when(userRepository.findById(executerId)).thenReturn(Optional.of(executerUser));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundByIdException.class, () -> {
            editUserInteractor.execute(editRequest, executerId, userId);
        });

        verify(userRepository, times(1)).findById(executerId);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should throw exception when executer lacks permission")
    void execute_shouldThrowException_whenExecuterLacksPermission() {
        UserEntity unauthorizedExecuter = new UserEntity(
                UUID.randomUUID(),
                new Name("Normal User"),
                new Email("normal@example.com"),
                Password.fromPlain("password", passwordEncoder, passwordValidator),
                TypeUser.NORMAL,
                null, null, 0
        );

        when(userRepository.findById(unauthorizedExecuter.getId())).thenReturn(Optional.of(unauthorizedExecuter));
        when(userRepository.findById(userId)).thenReturn(Optional.of(userToEdit));

        assertThrows(UserEditValidationException.class, () -> {
            editUserInteractor.execute(editRequest, unauthorizedExecuter.getId(), userId);
        });

        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
*/