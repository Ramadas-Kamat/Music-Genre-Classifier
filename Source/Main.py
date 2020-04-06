#from Source import Classification
#from Source import CreateDataset
#from Source import TrainModel

import Classification
import CreateDataset
import TrainModel


def main():
    CreateDataset.main()
    TrainModel.main()
    Classification.main()


if __name__ == '__main__':
    main()
