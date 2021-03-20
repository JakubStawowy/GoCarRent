import {Card, Container, makeStyles, Typography} from "@material-ui/core";
import image from '../uploads/user.png';
import StarIcon from '@material-ui/icons/Star';
import StarBorderIcon from '@material-ui/icons/StarBorder';

const useStyles = makeStyles((theme) => ({
    paper: {
        // height: '15vh',
        width: '100%',
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'flex-start',
        padding: '1em'
    },
    img: {
        objectFit: 'cover',
        borderRadius: '50%',
        height: '15vh'
    },
    content: {
        height: '100%',
        padding: '0',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around',
    },
    p: {
        padding: 0
    },
    feedbackTop: {
        display: 'flex',
        justifyContent: 'space-between'
    },
    stars: {
        color: '#4BBEBAE0',
    }
}));


export default function Feedback(props) {
    const classes = useStyles();
    return (
        <Card className={classes.paper}>
            <img src={image} alt={"No-image"} className={classes.img}/>
            <Container className={classes.content} style={{padding: '1em'}}>
                <Typography variant={'h6'} className={classes.feedbackTop}>
                    {props.author}
                    <div className={classes.stars}>
                        {
                            props.stars.map(star => {
                                if(star)
                                    return (
                                        <StarIcon />
                                    )
                                else
                                    return (
                                        <StarBorderIcon />
                                    )
                            })
                        }
                    </div>
                </Typography>
                <Typography variant={'body1'}>
                    {props.content}
                </Typography>
            </Container>
        </Card>
    )
}