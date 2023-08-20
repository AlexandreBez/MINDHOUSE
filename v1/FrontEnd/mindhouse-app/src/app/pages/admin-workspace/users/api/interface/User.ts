export interface User{
    name: string;
    email: string;
    password: string;
    role: string;
    is_temp_password: boolean;
    token_reset_password: string;
    expiration_time_reset_password: string;
    created_on: string;
}