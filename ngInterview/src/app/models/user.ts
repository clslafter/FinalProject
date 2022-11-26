import { Address } from "./address";
import { Answer } from "./answer";
import { JobOpening } from "./job-opening";
import { Question } from "./question";

export class User {
    id: number;
    firstName: string;
    lastName: string;
    enabled: boolean;
    role: boolean;
    username: string;
    password: string;
    email: string;
    aboutMe: string;
    dateCreated: string;
    avatarUrl: string;
    address?: Address;
    questions: Question[];
    answers: Answer[];
    jobs: JobOpening[];
    constructor(id: number = 0, firstName: string = '',lastName: string = '',
        enabled: boolean = true, role: boolean = true, username: string = '',
        password: string = '', email: string = '', aboutMe: string = '',
        dateCreated: string = '', avatarURL: string = '', address: Address,
        questions: Question[], answers: Answer[], jobs: JobOpening[]
         ){
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.enabled = enabled;
            this.role = role;
            this.username = username;
            this.password = password;
            this.email = email;
            this.aboutMe = aboutMe;
            this.dateCreated = dateCreated;
            this.avatarUrl = avatarURL;
            this.address = address;
            this.questions = questions;
            this.answers = answers;
            this.jobs = jobs;
         }

}
