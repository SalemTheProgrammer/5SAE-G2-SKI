import { Support } from "./support";
import { TypeCourse } from "./type-course";

export interface Course {
    idCourse: number;
    name: string;
    level: number;
    typeCourse: TypeCourse;
    support: Support;
    price: number;
}
