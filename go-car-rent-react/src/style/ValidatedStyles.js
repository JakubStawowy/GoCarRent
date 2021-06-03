import {makeStyles} from "@material-ui/core";

export const useValidatedStyles = makeStyles({
   correctTextField: {
       borderBottom: 'none'
   },
   wrongTextField: {
       borderBottom: '1px solid red'
   }
});