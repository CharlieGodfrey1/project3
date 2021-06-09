import LoadBar from './nice-spinner.gif'


const Loader = props => {
    return (
        <div className="spinner">
            <img src={LoadBar} />
        </div>
    )
}

export default Loader;